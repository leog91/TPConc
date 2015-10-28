package conc;

import java.util.ArrayList;
import java.util.List;

/** This class represents a fixed width vector of floating point numbers. */
public class ConcurVector {

	
	// An array with the vector elements
	private double[] elements;
	private CvThread[] threads;
	//private List<CvThread> threads= new ArrayList<CvThread>();
	
	
	
	/** Constructor for a ConcurVector.
	 * @param size, the width of the vector.
	 * @precondition size > 0. */
	public ConcurVector(int size,int threads) {
		elements = new double[size];
		this.threads = new CvThread[threads];
		for (int i = 0; i < threads; ++i){
			CvThread thread = new CvThread(this.elements);
			thread.start();
			this.threads[i]=thread;
			
			}
	}
		
	
	
	
	/** Returns the dimension of this vector, that is, its width. */
	public int dimension() {
		return elements.length;
	}
	
	
	/** Returns the element at position i.
	 * @param i, the position of the element to be returned.
	 * @precondition 0 <= i < dimension(). */
	public double get(int i) {
		return elements[i];
	}
	
	
	/** Assigns the value d to the position i of this vector. 
	 * @param i, the position to be set.
	 * @param d, the value to assign at i.
	 * @precondition 0 <= i < dimension. */
	public void set(int i, double d) {
		elements[i] = d;
	}
	
	
	/** Assigns the value d to every position of this vector. 
	 * @param d, the value to assigned. */
	public void set(double d) {
		for (int i = 0; i < dimension(); ++i)
			elements[i] = d;
	}
	
	
	/** Copies the values from another vector into this vector.
	 * @param v, a vector from which values are to be copied.
	 * @precondition dimension() == v.dimension(). */
	public void assign(ConcurVector v) {
		for (int i = 0; i < dimension(); ++i)
			set(i, v.get(i));
	}
	
	
	/** Copies the values from another vector into this vector
	 * if a it has a corresponding positive mask.
	 * @param mask, a vector of conditions that indicate whether an element has to be copied or not.
	 * @param v, a vector from which values are to be copied.
	 * @precondition dimension() == mask.dimension() && dimension() == v.dimension(). */
	public void assign(ConcurVector mask, ConcurVector v) {
		for (int i = 0; i < dimension(); ++i)
			if (mask.get(i) >= 0)
				set(i, v.get(i));
	}

	
	/** Loads threads. */

	public void loadThreadsWithRange(){
		
		int cantidadATrabajar =this.elements.length/this.threads.length;
		
		
		int indiceActual = 0;
		
		for(int i=0 ; i< this.threads.length -2 ; i++){
			this.threads[i].loadRange(indiceActual,indiceActual+cantidadATrabajar);
			indiceActual =+ cantidadATrabajar ;
		}
		
		this.threads[this.threads.length-1].loadRange(indiceActual,indiceActual+
				(this.elements.length%this.threads.length));
			
	}
	
	
	/** Applies the absolute value operation to every element in this vector. */
	 synchronized public void abs() {
		
		
		 
		 this.loadThreadsWithRange();
		
		 for(int i=0 ; i < this.threads.length ; i ++)
		 
			 this.threads[i].abs();
		 
		 
		 for (int i = 0; i < dimension(); ++i)
			set(i, Math.abs(get(i)));
		
		
		
		
		
	}
	
	
	/** Adds the elements of this vector with the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == v.dimension(). */
	public void add(ConcurVector v) {
		for (int i = 0; i < dimension(); ++i)
			set(i, get(i) + v.get(i));
	}
	
	
	/** Subtracts from the elements of this vector the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == v.dimension(). */
	public void sub(ConcurVector v) {
		for (int i = 0; i < dimension(); ++i)
			set(i, get(i) - v.get(i));
	}
	
	
	/** Multiplies the elements of this vector by the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == v.dimension(). */
	public void mul(ConcurVector v) {
		for (int i = 0; i < dimension(); ++i)
			set(i, get(i) * v.get(i));
	}
	
	
	/** Divides the elements of this vector by the values of another (element-wise).
	 * @param v, a vector from which to get the second operands.
	 * @precondition dimension() == v.dimension(). */
	public void div(ConcurVector v) {
		for (int i = 0; i < dimension(); ++i)
			set(i, get(i) / v.get(i));
	}
	
	
	/** Returns the sum of all the elements in this vector. */
	public double sum() {
		double result = 0;
		for (int i = 0; i < dimension(); ++i)
			result += get(i);
		return result;
	}
	
	
	/** Returns the dot product between two vectors (this and v).
	 * @param v, second operand of the dot product operation.
	 * @precondition dimension() == v.dimension(). */
	
//	
//	public double prod(ConcurVector v) {
//		ConcurVector aux = new ConcurVector(dimension());
//		aux.assign(this);
//		aux.mul(v);
//		return aux.sum();
//	}
//	
	
	
	
//	/** Returns the norm of this vector. */
//	public double norm() {
//		ConcurVector aux = new ConcurVector(dimension());
//		aux.assign(this);
//		aux.mul(this);
//		return Math.sqrt(aux.sum());
//	}
//	
	
//	/** Normalizes this vector, converting it into a unit vector. */
//	public void normalize() {
//		ConcurVector aux = new ConcurVector(dimension());
//		aux.set(this.norm());
//		div(aux);
//	}
//	
	
	/** Applies the max operation element-wise.
	 * @param v, a vector with the second operands for max.
	 * @precondition dimension == v.dimension(). */
	public void max(ConcurVector v) {
		for (int i = 0; i < dimension(); ++i)
			set(i, Math.max(get(i), v.get(i)));
	}
	
	
	/** Applies the min operation element-wise.
	 * @param v, a vector with the second operands for min.
	 * @precondition dimension == v.dimension(). */
	public void min(ConcurVector v) {
		for (int i = 0; i < dimension(); ++i)
			set(i, Math.min(get(i), v.get(i)));
	}
	
	
}
