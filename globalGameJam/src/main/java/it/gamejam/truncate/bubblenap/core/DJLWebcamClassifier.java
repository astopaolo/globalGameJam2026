package it.gamejam.truncate.bubblenap.core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

import ai.djl.Model;
import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.ImageFactory;
import ai.djl.modality.cv.transform.Normalize;
import ai.djl.modality.cv.transform.Resize;
import ai.djl.modality.cv.translator.ImageClassificationTranslator;
import ai.djl.translate.Translator;

public class DJLWebcamClassifier extends JFrame {

	private Predictor<Image, Classifications> predictor;
	private JLabel resultLabel;
	private Webcam webcam;

	private boolean isRunning = true;
	private GameManager gameManager;

	public DJLWebcamClassifier(GameManager gameManager) throws Exception {
		super("DJL Webcam Classifier");
		this.gameManager = gameManager;

		// 1. Inizializza il Modello DJL
		initModel();

		// 2. Setup Webcam e GUI
		webcam = Webcam.getDefault();
		webcam.setViewSize(new Dimension(640, 480));

//		WebcamPanel panel = new WebcamPanel(webcam);
//		panel.setFPSDisplayed(true);
//
//		resultLabel = new JLabel("In attesa del frame...", SwingConstants.CENTER);
//		resultLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
//		resultLabel.setPreferredSize(new Dimension(640, 50));
//
//		setLayout(new BorderLayout());
//		add(panel, BorderLayout.CENTER);
//		add(resultLabel, BorderLayout.SOUTH);
//
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		pack();
//		setLocationRelativeTo(null);
//		setVisible(true);

		// 3. Avvia il loop di predizione
		startInferenceLoop();
	}

	private void initModel() throws Exception {
		// Percorso della cartella che contiene model_unquant.tflite e synset.txt
		Path modelDir = Paths.get("modello_facce/model.savedmodel/");
		float[] mean = { 127.5f, 127.5f, 127.5f };
		float[] std = { 127.5f, 127.5f, 127.5f };
		// Il Translator gestisce il preprocessing (Resize e Normalizzazione)
		Translator<Image, Classifications> translator = ImageClassificationTranslator.builder()
				.addTransform(new Resize(224, 224)).addTransform(new Normalize(mean, std)).build();

		Model model = Model.newInstance("teachable-machine");
		// Specifichiamo il file del modello
		model.load(modelDir, "model_unquant.tflite");

		this.predictor = model.newPredictor(translator);
	}
	
	public Webcam getWebcam() {
		return webcam;
	}
	
	private void startInferenceLoop() {
		new Thread(() -> {
			while (isRunning) {
				BufferedImage bufferedImage = webcam.getImage();
				if (bufferedImage != null) {
					try {
						// Converte BufferedImage nel formato DJL Image
						Image img = ImageFactory.getInstance().fromImage(bufferedImage);

						// Esegue la classificazione
						Classifications result = predictor.predict(img);
						Classifications.Classification best = result.best();
						gameManager.setMask(best.getClassName());
						// Aggiorna l'etichetta
//						String text = String.format("%s (%.2f%%)", best.getClassName(), best.getProbability() * 100);

//						SwingUtilities.invokeLater(() -> resultLabel.setText(text));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				// Piccolo delay per non sovraccaricare la CPU
				try {
					Thread.sleep(50);
				} catch (InterruptedException ignored) {
				}
			}
		}).start();
	}
}