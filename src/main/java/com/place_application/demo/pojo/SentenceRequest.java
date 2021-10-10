package com.place_application.demo.pojo;

public class SentenceRequest {
    private int task_no; // 审批任务编号
    private int decision; // 审批决定 1 通过 2 打回 3 拒绝
    private String refuseReason; //打回 或 拒绝理由

    public int getTask_no() {
        return task_no;
    }

    public void setTask_no(int task_no) {
        this.task_no = task_no;
    }

    public int getDecision() {
        return decision;
    }

    public void setDecision(int decision) {
        this.decision = decision;
    }

    public String getRefuseReason() {
        return refuseReason;
    }


    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    @Override
    public String toString() {
        return "SentenceRequest{" +
                "task_no=" + task_no +
                ", decision=" + decision +
                ", refuseReason='" + refuseReason + '\'' +
                '}';
    }

}
