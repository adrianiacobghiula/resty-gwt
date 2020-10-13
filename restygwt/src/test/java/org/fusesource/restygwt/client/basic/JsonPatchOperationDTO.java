package org.fusesource.restygwt.client.basic;

public class JsonPatchOperationDTO {
    private String op;
    private String path;
    private Object value;
    private String from;

    public JsonPatchOperationDTO op(String op) {
        this.op = op;
        return this;
    }

    public JsonPatchOperationDTO path(String path) {
        this.path = path;
        return this;
    }

    public JsonPatchOperationDTO value(Object value) {
        this.value = value;
        return this;
    }

    public JsonPatchOperationDTO from(String from) {
        this.from = from;
        return this;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
