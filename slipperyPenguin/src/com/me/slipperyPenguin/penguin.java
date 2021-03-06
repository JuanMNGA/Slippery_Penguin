package com.me.slipperyPenguin;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class penguin { // Esta clase representa nuestro pingüino del juego.
	private static final float SPEED = 500; // Velocidad de movimiento del pingüino.
	private static final float LIMITE_IZQUIERDA = 0; // Límeta de la pantalla a la izquierda.
	private static final float LIMITE_DERECHA = Gdx.graphics.getWidth(); // Límeta de la pantalla a la derecha.
	
	private Vector2 posicion; // Representa la posición del pingüino en el eje x y en el eje y
	private float anchura, altura; // Anchura y altura del pingüino.
	private Rectangle bordes; // Rectangulo que ocupara el ancho y alto del pingüino.
	
	public penguin(Vector2 posicion, float anchura, float altura) { // Constructor
		this.posicion = posicion;
		this.anchura = anchura;
		this.altura = altura;
		bordes = new Rectangle(posicion.x, posicion.y, anchura, altura);
	}
	
	public void update() { // Función que se ejecuta en Render de GameScren, y sirve para actualizar los valores del pingüino.
		if(Gdx.app.getType() == ApplicationType.Desktop) // Si estamos jugando en nuestro ordenador
			entradaDesktop();
		else if(Gdx.app.getType() == ApplicationType.Android) // Si se esta ejecutando en android
			entradaAndroid();
		
		// Actualizamos los bordes (la anchura y altura ya se definen en el constructor de Entidad
		bordes.x = posicion.x;
		bordes.y = posicion.y;
	}
	
	private void entradaDesktop() { // Acciones del pingüino si estamos ejecutando el juego en nuestro PC
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) { // Si presionamos la tecla derecha
			float nuevaPosicion = posicion.x + SPEED * Gdx.graphics.getDeltaTime(); // a la posicion en x le sumo la velocidad que hemos determinado por SPEED.
			if(noChoqueConLimiteDerecho(nuevaPosicion)) // Si no choca con el limite derecho.
				posicion.x = nuevaPosicion;
			else
				posicion.x = LIMITE_DERECHA - anchura;
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)) { // Si presionamos la tecla izquierda.
			float nuevaPosicion = posicion.x - SPEED  * Gdx.graphics.getDeltaTime(); // lo mismo pero restandole, ya que vamos a la izquierda
			if(noChoqueConLimiteIzquierdo(nuevaPosicion)) // Si no choca con el limite izquierdo.
				posicion.x = nuevaPosicion;
			else
				posicion.x = LIMITE_IZQUIERDA;
		}
	}
	private void entradaAndroid() { // Acciones del pingüino si estamos ejecutando el juego en Android            
        // Al mover el movil hacia la derecha el valor es positivo. Si está más inclinado se mueve más rápido
        if (Gdx.input.getAccelerometerY() > 0.75) {
        	float nuevaPosicion = posicion.x + Math.abs(Gdx.input.getAccelerometerY()) * SPEED  * Gdx.graphics.getDeltaTime(); // La nueva posición del pingüino.
        	if(noChoqueConLimiteDerecho(nuevaPosicion)) // Si la nueva posición es menor que el límite derecho.
        		posicion.x = nuevaPosicion;
        	else
        		posicion.x = LIMITE_DERECHA - anchura;
        }
        // Al mover el movil hacia la izquierda el valor es negativo. Si está más inclinado se mueve más rápido.           
        if (Gdx.input.getAccelerometerY() < -0.75) {
        	float nuevaPosicion = posicion.x - Math.abs(Gdx.input.getAccelerometerY()) * SPEED  * Gdx.graphics.getDeltaTime(); // La nueva posición del pingüino.
        	if(noChoqueConLimiteIzquierdo(nuevaPosicion)) // Si la nueva posición es menor que el límite izquierdo.
        		posicion.x = nuevaPosicion; 
        	else
        		posicion.x = LIMITE_IZQUIERDA;
        } 
	}
	
	private boolean noChoqueConLimiteDerecho(float nuevaPosicion) { // Permite que el pingüino no avance y se salga de la pantalla por la derecha
		return nuevaPosicion + anchura < LIMITE_DERECHA;
	}
	private boolean noChoqueConLimiteIzquierdo(float nuevaPosicion) { // Permite que el pingüino no avance y se salga de la pantalla por la izquierda.
		return nuevaPosicion > LIMITE_IZQUIERDA;
	}
	
	public boolean choqueConRoca(rock roca) { // Método que permite conocer si nuestro pingüino se ha chocado con alguna roca. 
		return bordes.overlaps(roca.getBordes());
	}
	
	public void setPosicion(float x, float y) { // Asigna nueva posición al pingüino.
		posicion.x = x;
		posicion.y = y;
	}
		
	// Getters -----------------------------------

	public Vector2 getPosicion() {
		return posicion;
	}

	public float getAnchura() {
		return anchura;
	}

	public float getAltura() {
		return altura;
	}

	public Rectangle getBordes() {
		return bordes;
	}
}
