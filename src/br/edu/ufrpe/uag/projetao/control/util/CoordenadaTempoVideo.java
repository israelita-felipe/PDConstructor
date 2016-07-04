/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.util;

/**
 * @author israel
 *
 */
public class CoordenadaTempoVideo {

    private Integer[] coordenadas;
    private double tempo;

    /**
     * 
     */
    public CoordenadaTempoVideo(Integer[] coordenadas, double tempo) {
	this.coordenadas = coordenadas;
	this.tempo = tempo;
    }

    /**
     * @return the coordenadas
     */
    public Integer[] getCoordenadas() {
	return coordenadas;
    }

    /**
     * @param coordenadas
     *            the coordenadas to set
     */
    public void setCoordenadas(Integer[] coordenadas) {
	this.coordenadas = coordenadas;
    }

    /**
     * @return the tempo
     */
    public double getTempo() {
	return tempo;
    }

    /**
     * @param tempo
     *            the tempo to set
     */
    public void setTempo(double tempo) {
	this.tempo = tempo;
    }

    @Override
    public String toString() {
	int segundo = (int) (getTempo() % 60);
	int minutos = (int) (getTempo() / 60);
	int minuto = minutos % 60;
	int hora = minutos / 60;
	return String.format("%02d:%02d:%02d | [(%d,%d),(%d,%d)", hora, minuto, segundo, coordenadas[0], coordenadas[1],
		coordenadas[2], coordenadas[3]);
    }
}
