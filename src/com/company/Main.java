package com.company;

import java.io.*;
import java.util.LinkedList;
import java.util.Vector;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
        Vector<String> board = new Vector<String>();
        Element root;
        LinkedList<Element> visited = new LinkedList<Element>();
        LinkedList<Element> bfs_list;

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        String line;


        while(br.ready()) {
            line = br.readLine();
            board.add(line);
        } // End while

        root = new Element(board, getStartPosition(board));

        //Checks if player is already at goal
        if (board.get(root.getPosition().lastElement()).charAt(root.getPosition().firstElement()) == '+'){ System.out.println(" "); return;}

        root.setType('@');
        root.setParent(null);

        bfs_list = new LinkedList<Element>();
        bfs_list.add(root);
        visited.add(root);
        //System.out.println(root.getPosition());

        /* print the maze
        for ( String lines : board){
            for ( int j = 0; j < lines.length(); j++){
                System.out.print(lines.charAt(j));
            }
            System.out.println();
        }
        */
        //Moving cycle
        Element current_node = new Element();
        while (!bfs_list.isEmpty()){
            current_node = bfs_list.removeFirst();
            //Is in goal? (Or is born in goal?)
            if (board.get(current_node.getPosition().lastElement()).charAt(current_node.getPosition().firstElement()) == '.'){
                //If break happens, current_node variable holds the last node (the end)
                break;
            }

            if (current_node.canMoveRight()){
                Vector<Integer> right =  new Vector<Integer>();
                right.add(current_node.getPosition().firstElement() + 1);
                right.add(current_node.getPosition().lastElement());

                Element next_node = new Element(right);
                next_node.setParent(current_node);
                if (!next_node.isIn(visited)){
                    visited.add(next_node);
                    bfs_list.add(next_node);
                }
            }
            if (current_node.canMoveUp()){
                Vector<Integer> up =  new Vector<Integer>();
                up.add(current_node.getPosition().firstElement());
                up.add(current_node.getPosition().lastElement() - 1);

                Element next_node = new Element(up);
                next_node.setParent(current_node);
                if (!next_node.isIn(visited)){
                    visited.add(next_node);
                    bfs_list.add(next_node);
                }
            }
            if (current_node.canMoveLeft()){
                Vector<Integer> left =  new Vector<Integer>();
                left.add(current_node.getPosition().firstElement() - 1);
                left.add(current_node.getPosition().lastElement());

                Element next_node = new Element(left);
                next_node.setParent(current_node);
                if (!next_node.isIn(visited)){
                    visited.add(next_node);
                    bfs_list.add(next_node);
                }
            }
            if (current_node.canMoveDown()){
                Vector<Integer> down =  new Vector<Integer>();
                down.add(current_node.getPosition().firstElement());
                down.add(current_node.getPosition().lastElement() + 1);

                Element next_node = new Element(down);
                next_node.setParent(current_node);
                if (!next_node.isIn(visited)){
                    visited.add(next_node);
                    bfs_list.add(next_node);
                }
            }

            if (bfs_list.isEmpty()){
                System.out.println("no path");
                return;
            }
        }
        //System.out.println("posicion nodo final: " + current_node.getPosition());
        System.out.println(buildPath(current_node));
    } // main

    //Variable i holds the index for the string, this is the y coordenate
    //Variable j holds the index of the char at the Vector[y] string, this is the x coordanate
    public static Vector<Integer> getStartPosition( Vector<String> board)
    {
        Vector<Integer> result = new Vector<Integer>();
        int j;

        for ( int i = 0; i < board.size(); i++){
            String line = board.get(i);

            for(j = 0; j < line.length(); j++){

                if (line.charAt(j) == '@' || line.charAt(j) == '+'){
                    result.add(j);
                    result.add(i);
                    break;
                }
            }
        }
        return result;
    }

    public static String buildPath(Element tail){
        String response = " ";
        if (tail.getParent() == null){
            return response;
        }
        while (tail.getParent() != null){
            if (tail.getPosition().firstElement() < tail.getParent().getPosition().firstElement()){
                response += "L ";
                tail = tail.getParent();
            }
            else if (tail.getPosition().firstElement() > tail.getParent().getPosition().firstElement()){
                response += "R ";
                tail = tail.getParent();
            }
            else if (tail.getPosition().lastElement() < tail.getParent().getPosition().lastElement()){
                response += "U ";
                tail = tail.getParent();
            }
            else if (tail.getPosition().lastElement() > tail.getParent().getPosition().lastElement()){
                response += "D ";
                tail = tail.getParent();
            }
        }
        StringBuilder response_st = new StringBuilder(response);
        return response_st.reverse().toString();
    }
} // End Main
