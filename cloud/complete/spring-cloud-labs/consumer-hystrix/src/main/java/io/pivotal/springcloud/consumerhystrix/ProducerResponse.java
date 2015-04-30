package io.pivotal.springcloud.consumerhystrix;

public class ProducerResponse {
    public ProducerResponse() {
    }

    public ProducerResponse(Integer value) {

        this.value = value;
    }

    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
