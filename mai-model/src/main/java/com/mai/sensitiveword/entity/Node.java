package com.mai.sensitiveword.entity;

import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sks on 2015/12/8.
 */
public class Node implements Serializable {
    private static final long serialVersionUID = -8797319137159007603L;

    private Map<String,Node> children = new HashMap<String,Node>(0);
    private boolean isEnd = false;
    private int level =0;

    public Node addChar(char c) {
        String cStr = String.valueOf(c);
        Node node = children.get(cStr);
        if(node == null){
            node = new Node();
            children.put(cStr, node);
        }
        return node;
    }

    public Node findChar(char c){
        String cStr = String.valueOf(c);
        return children.get(cStr);
    }

    public Map<String, Node> getChildren() {
        return children;
    }

    public void setChildren(Map<String, Node> children) {
        this.children = children;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public static void main(String[] args) {
        byte[] b = new byte[0];
        try {
            b = new BASE64Decoder().decodeBuffer("哇哈哈");
            String value = new String(b);
            System.out.println(value);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}