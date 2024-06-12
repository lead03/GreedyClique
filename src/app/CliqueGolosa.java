package app;

import java.awt.EventQueue;

import app.interfaz.Enrutador;

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
