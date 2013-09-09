package com.company;
import java.util.LinkedList;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Sebastián González Mardones
 * Date: 9/6/13
 * Time: 4:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class Element {

    //You need to have a map in order to build an Element
    private static Vector<String> map;
    private char type;
    private Vector<Integer> position;
    private Element parent;

    //Builders
    public Element( Vector<String> map_in, Vector<Integer> position_in){
        map = map_in;
        this.position = position_in;
    }

    public Element( Vector<Integer> position_in){
        this.position = position_in;
    }

    public Element(){
    }

    //Getters och Setters och Class Functions
    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public Element getParent() {
        return parent;
    }

    public void setParent(Element parent) {
        this.parent = parent;
    }

    public Vector<Integer> getPosition() {
        return position;
    }

    public void setPosition(Vector<Integer> position) {
        this.position = position;
    }

    public Vector<String> getMap() {
        return map;
    }

    public boolean canMoveRight(){
        //indice de string en vector de string es coordenada y de posicion
        //indice de caracter en la string es coordenada x
        return  (map.get(getPosition().lastElement()).charAt(getPosition().firstElement() + 1) == ' ' || map.get(getPosition().lastElement()).charAt(getPosition().firstElement() + 1) == '.');
    }

    public boolean canMoveLeft(){
        return (map.get(getPosition().lastElement()).charAt(getPosition().firstElement() - 1) == ' ' || map.get(getPosition().lastElement()).charAt(getPosition().firstElement() - 1) == '.');
    }

    public boolean canMoveUp(){
        return (map.get(getPosition().lastElement() - 1).charAt(getPosition().firstElement()) == ' ' || map.get(getPosition().lastElement() - 1).charAt(getPosition().firstElement()) == '.');
    }

    public boolean canMoveDown(){
        return (map.get(getPosition().lastElement() + 1).charAt(getPosition().firstElement()) == ' ' || map.get(getPosition().lastElement() + 1).charAt(getPosition().firstElement()) == '.');
    }

    public boolean isIn(LinkedList<Element> visited){
        for (Element elem : visited){
            if (this.equalsP(elem)){
                return true;
            }
        }
        return false;
    }

    public boolean equalsP(Element el){
        return (this.getPosition().equals(el.getPosition()));
    }

}
