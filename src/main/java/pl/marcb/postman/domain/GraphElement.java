package pl.marcb.postman.domain;

public class GraphElement {
    private String pointA;
    private String pointB;
    private int distanceFromAtoB;
    private int distanceFromBtoA;

    public GraphElement() {
    }

    public GraphElement(String pointA, String pointB, int distanceFromAtoB, int distanceFromBtoA) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.distanceFromAtoB = distanceFromAtoB;
        this.distanceFromBtoA = distanceFromBtoA;
    }

    public String getPointA() {
        return pointA;
    }

    public void setPointA(String pointA) {
        this.pointA = pointA;
    }

    public String getPointB() {
        return pointB;
    }

    public void setPointB(String pointB) {
        this.pointB = pointB;
    }

    public int getDistanceFromAtoB() {
        return distanceFromAtoB;
    }

    public void setDistanceFromAtoB(int distanceFromAtoB) {
        this.distanceFromAtoB = distanceFromAtoB;
    }

    public int getDistanceFromBtoA() {
        return distanceFromBtoA;
    }

    public void setDistanceFromBtoA(int distanceFromBtoA) {
        this.distanceFromBtoA = distanceFromBtoA;
    }
}
