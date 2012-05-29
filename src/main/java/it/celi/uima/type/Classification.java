

/* First created by JCasGen Tue May 29 09:42:00 CEST 2012 */
package it.celi.uima.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue May 29 09:42:00 CEST 2012
 * XML source: /home/frank/projects/celi/uima-drools/src/main/resources/it/celi/uima/type/Classification.xml
 * @generated */
public class Classification extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Classification.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Classification() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Classification(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Classification(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Classification(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: label

  /** getter for label - gets 
   * @generated */
  public String getLabel() {
    if (Classification_Type.featOkTst && ((Classification_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "it.celi.uima.type.Classification");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Classification_Type)jcasType).casFeatCode_label);}
    
  /** setter for label - sets  
   * @generated */
  public void setLabel(String v) {
    if (Classification_Type.featOkTst && ((Classification_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "it.celi.uima.type.Classification");
    jcasType.ll_cas.ll_setStringValue(addr, ((Classification_Type)jcasType).casFeatCode_label, v);}    
  }

    