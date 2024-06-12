package app.interfaz;

import java.awt.EventQueue;

public class CliqueGolosa {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					CliqueGolosa clique = new CliqueGolosa();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public CliqueGolosa() {
		Enrutador enrutador= Enrutador.getInstancia();
		enrutador.start();
	}

}
