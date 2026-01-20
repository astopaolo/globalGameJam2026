import static org.bytedeco.opencv.global.opencv_core.CV_32F;
import static org.bytedeco.opencv.global.opencv_core.minMaxLoc;
import static org.bytedeco.opencv.global.opencv_dnn.DNN_BACKEND_CUDA;
import static org.bytedeco.opencv.global.opencv_dnn.DNN_TARGET_CUDA;
import static org.bytedeco.opencv.global.opencv_dnn.blobFromImage;
import static org.bytedeco.opencv.global.opencv_dnn.readNetFromCaffe;
import static org.bytedeco.opencv.global.opencv_imgproc.FONT_HERSHEY_SIMPLEX;
import static org.bytedeco.opencv.global.opencv_imgproc.LINE_8;
import static org.bytedeco.opencv.global.opencv_imgproc.LINE_AA;
import static org.bytedeco.opencv.global.opencv_imgproc.circle;
import static org.bytedeco.opencv.global.opencv_imgproc.line;
import static org.bytedeco.opencv.global.opencv_imgproc.putText;

import java.io.File;

import javax.swing.JFrame;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_dnn.Net;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;

/**
 * Applicazione per il riconoscimento delle pose del viso e del corpo usando
 * JavaCV Implementa sia face detection che pose estimation
 */
public class JavaCVPoseDetection {

	// Configurazione per pose detection 160000

	private static final int POSE_PAIRS[][] = { { 0, 1 }, { 1, 14 }, { 1, 2 }, { 1, 5 }, { 2, 3 }, { 3, 4 }, { 5, 6 },
			{ 6, 7 }, { 14, 8 }, { 8, 9 }, { 9, 10 }, { 14, 11 }, { 11, 12 }, { 12, 13 } };
	private static final String[] BODY_PARTS = { "Testa", "Collo", "Spalla_Dx", "Gomito_Dx", "Polso_Dx", "Spalla_Sx",
			"Gomito_Sx", "Polso_Sx", "Anca_Dx", "Ginocchio_Dx", "Caviglia_Dx", "Anca_Sx", "Ginocchio_Sx", "Caviglia_Sx",
			"Torace" };

	// Configurazione per pose detection 440000
//	private static final int POSE_PAIRS[][] = { { 1, 2 }, { 1, 5 }, { 2, 3 }, { 3, 4 }, { 5, 6 }, { 6, 7 }, { 1, 8 },
//			{ 8, 9 }, { 9, 10 }, { 1, 11 }, { 11, 12 }, { 12, 13 }, { 1, 0 }, { 0, 14 }, { 14, 16 }, { 0, 15 },
//			{ 15, 17 } };
//	private static final String[] BODY_PARTS = { "Naso", "Collo", "Spalla_Dx", "Gomito_Dx", "Polso_Dx", "Spalla_Sx",
//			"Gomito_Sx", "Polso_Sx", "Anca_Dx", "Ginocchio_Dx", "Caviglia_Dx", "Anca_Sx", "Ginocchio_Sx", "Caviglia_Sx",
//			"Occhio_Dx", "Occhio_Sx", "Orecchio_Dx", "Orecchio_Sx" };

	private static final int IN_WIDTH = 128;
	private static final int IN_HEIGHT = 128;
	private static final double THRESHOLD = 0.2;

	/**
	 * Main method
	 */
	public static void main(String[] args) {
		try {
			JavaCVPoseDetection app = new JavaCVPoseDetection();

			System.out.println("╔════════════════════════════════════════════╗");
			System.out.println("║  JavaCV -      Pose Detection              ║");
			System.out.println("╚════════════════════════════════════════════╝\n");

			// Percorsi dei modelli (modifica questi percorsi)
			String poseProto = "model/pose_deploy_linevec_faster_4_stages.prototxt";
			String poseWeights = "model/pose_iter_160000.caffemodel";
//			String poseProto = "pose/pose_deploy_linevec.prototxt";
//			String poseWeights = "pose/pose_iter_440000.caffemodel";

			// Controlla se i file esistono
			File protoFile = new File(poseProto);
			File weightsFile = new File(poseWeights);

			if (!protoFile.exists() || !weightsFile.exists()) {
				System.out.println("⚠ ATTENZIONE: File modello OpenPose non trovati!");
				poseProto = null;
				poseWeights = null;
			}

			// Carica i modelli
			app.loadModels(poseProto, poseWeights);

			// Inizializza la webcam (0 = webcam predefinita)
			System.out.println("Inizializzazione webcam...");
			app.initializeCamera(0);

			// Avvia il loop principale
			app.run();

		} catch (Exception e) {
			System.err.println("Errore nell'applicazione: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private FrameGrabber grabber;
	private CanvasFrame canvas;
	private Net poseNet;
	private CascadeClassifier faceCascade;
	private boolean usePoseDetection = true;

	public JavaCVPoseDetection() {
		Loader.load(org.bytedeco.opencv.opencv_java.class);
	}

	/**
	 * Pulizia delle risorse
	 */
	private void cleanup() {
		try {
			if (grabber != null) {
				grabber.stop();
				grabber.release();
			}
			if (canvas != null) {
				canvas.dispose();
			}
			if (poseNet != null && !poseNet.empty()) {
				poseNet.close();
			}
			if (faceCascade != null && !faceCascade.empty()) {
				faceCascade.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Rileva le pose del corpo usando OpenPose
	 */
	private void detectPose(Mat frame) {
		if (!usePoseDetection || poseNet == null || poseNet.empty()) {
			return;
		}

		int frameWidth = frame.cols();
		int frameHeight = frame.rows();

		// Prepara l'input per la rete
		Mat inputBlob = blobFromImage(frame, 1.0 / 255.0, new Size(IN_WIDTH, IN_HEIGHT), new Scalar(0, 0, 0, 0), false,
				false, CV_32F);

		poseNet.setInput(inputBlob);
		// Esegui forward pass
		Mat output = poseNet.forward();

		int H = output.size(2);
		int W = output.size(3);

		// Array per memorizzare i punti rilevati
		Point[] points = new Point[BODY_PARTS.length];

		// Trova i punti chiave
		for (int n = 0; n < BODY_PARTS.length; n++) {
			Mat probMap = new Mat(H, W, CV_32F, output.ptr(0, n));
			Point minLoc = new Point();
			Point maxLoc = new Point();
			double[] minVal = new double[1];
			double[] maxVal = new double[1];

			minMaxLoc(probMap, minVal, maxVal, minLoc, maxLoc, null);

			if (maxVal[0] > THRESHOLD) {
				int x = maxLoc.x() * frameWidth / W;
				int y = maxLoc.y() * frameHeight / H;
				points[n] = new Point(x, y);

				// Disegna il punto
				circle(frame, points[n], 8, new Scalar(0, 255, 255, 0), -1, LINE_8, 0);
				putText(frame, String.valueOf(n), points[n], FONT_HERSHEY_SIMPLEX, 0.6, new Scalar(0, 0, 255, 0), 2,
						LINE_AA, false);
			}

			probMap.release();
		}

		// Disegna lo scheletro
		for (int[] pair : POSE_PAIRS) {
			int partA = pair[0];
			int partB = pair[1];

			if (points[partA] != null && points[partB] != null) {
				line(frame, points[partA], points[partB], new Scalar(0, 255, 0, 0), 3, LINE_8, 0);
			}
		}
		inputBlob.release();
		output.release();
	}

	/**
	 * Inizializza la webcam
	 */
	public void initializeCamera(int deviceId) throws FrameGrabber.Exception {
		grabber = new OpenCVFrameGrabber(deviceId);
		grabber.setImageWidth(640);
		grabber.setImageHeight(480);
		grabber.start();

		canvas = new CanvasFrame("JavaCV - Face & Pose Detection", CanvasFrame.getDefaultGamma() / grabber.getGamma());
		canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas.setCanvasSize(640, 480);
	}

	/**
	 * Carica i modelli necessari
	 */
	public void loadModels(String poseProtoFile, String poseWeightsFile) {
		try {
			// Carica il modello per pose detection (OpenPose)
			if (poseProtoFile != null && poseWeightsFile != null) {
				File protoFile = new File(poseProtoFile);
				File weightsFile = new File(poseWeightsFile);

				if (protoFile.exists() && weightsFile.exists()) {
					poseNet = readNetFromCaffe(poseProtoFile, poseWeightsFile);
					poseNet.setPreferableBackend(DNN_BACKEND_CUDA);
					poseNet.setPreferableTarget(DNN_TARGET_CUDA);
					System.out.println("✓ Modello OpenPose caricato con successo");
					usePoseDetection = true;
				} else {
					System.out.println("⚠ File modello OpenPose non trovati, pose detection disabilitata");
					usePoseDetection = false;
				}
			} else {
				usePoseDetection = false;
			}

		} catch (Exception e) {
			System.err.println("Errore nel caricamento dei modelli: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Loop principale per catturare e processare i frame
	 */
	public void run() {
		OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();

		try {
			Frame frame;
			Mat mat;
			int frameCount = 0;

			System.out.println("\n=== Controlli ===");
			System.out.println("Premi 'ESC' per uscire");
			System.out.println("Chiudi la finestra per terminare");
			System.out.println("\nElaborazione in corso...\n");

			while ((frame = grabber.grab()) != null && canvas.isVisible()) {
				long startTime = System.currentTimeMillis();
				System.out.println("start " + startTime);
				mat = converter.convert(frame);
				opencv_core.flip(mat, mat, 1);
				if (mat != null && !mat.empty()) {

					// Rileva pose del corpo (più lento, ogni N frame)
					if (usePoseDetection && frameCount % 1 == 0) {
						detectPose(mat);
					}
					frameCount++;

					// Mostra il frame
					canvas.showImage(converter.convert(mat));
					mat.release();
					System.out.println("end " + (System.currentTimeMillis() - startTime));
				}

				// Piccola pausa
//				Thread.sleep(5);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanup();
			converter.close();
		}
	}
}