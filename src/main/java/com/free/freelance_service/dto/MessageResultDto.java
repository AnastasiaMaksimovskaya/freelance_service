package com.free.freelance_service.dto;

import java.util.List;

public class MessageResultDto<T> extends Message {
    T object;
    List<T> objects;
    long total;

    public MessageResultDto() {
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public List<T> getObjects() {
        return objects;
    }

    public void setObjects(List<T> objects) {
        this.objects = objects;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
