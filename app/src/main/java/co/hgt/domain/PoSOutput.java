package co.hgt.domain;

public class PoSOutput {
    String subject;
    String object;
    String verb;
    double magnitude;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject.replace("'","")
                              .replace("(","")
                              .replace(")","")
                              .replace("[","")
                              .replace("]","");;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object.replace("'","")
                            .replace("(","")
                            .replace(")","")
                            .replace("[","")
                            .replace("]","");;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb.replace("'","")
                        .replace("(","")
                        .replace(")","")
                        .replace("[","")
                        .replace("]","");
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }
}
