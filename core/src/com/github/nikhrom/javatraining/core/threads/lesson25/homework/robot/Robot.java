package com.github.nikhrom.javatraining.core.threads.lesson25.homework.robot;

import java.util.Optional;

public class Robot {

    private Optional<Body> body = Optional.ofNullable(null);
    private Optional<Cpu> cpu = Optional.ofNullable(null);
    private Optional<Hdd> hdd = Optional.ofNullable(null);
    private Optional<Head> head = Optional.ofNullable(null);
    private Optional<LeftHand> leftHand = Optional.ofNullable(null);
    private Optional<LeftLeg> leftLeg = Optional.ofNullable(null);
    private Optional<Ram> ram = Optional.ofNullable(null);
    private Optional<RightHand> rightHand = Optional.ofNullable(null);
    private Optional<RightLeg> rightLeg = Optional.ofNullable(null);


    public boolean isAssembled(){
        return body.isPresent() &&
                cpu.isPresent() &&
                hdd.isPresent() &&
                head.isPresent() &&
                leftHand.isPresent() &&
                leftLeg.isPresent() &&
                ram.isPresent() &&
                rightHand.isPresent() &&
                rightLeg.isPresent();
    };

    public boolean addPart(RobotPart part){
        if(part instanceof Body){
            return setBody((Body)part);
        }else if(part instanceof Cpu){
            return setCpu((Cpu)part);
        }else if(part instanceof Hdd){
            return setHdd((Hdd) part);
        }else if(part instanceof Head){
            return setHead((Head)part);
        }else if(part instanceof LeftHand){
            return setLeftHand((LeftHand) part);
        }else if(part instanceof LeftLeg){
            return setLeftLeg((LeftLeg) part);
        }else if(part instanceof Ram){
            return setRam((Ram)part);
        }else if(part instanceof RightHand){
            return setRightHand((RightHand)part);
        }else if(part instanceof RightLeg){
            return setRightLeg((RightLeg)part);
        }

        return false;
    };

    public Optional<Body> getBody() {
        return body;
    }

    public boolean setBody(Body body) {
        if(!this.body.isPresent()) {
            this.body = Optional.ofNullable(body);
            return true;
        }else{
            return false;
        }
    }

    public Optional<Cpu> getCpu() {
        return cpu;
    }

    public boolean setCpu(Cpu cpu) {
        if(!this.cpu.isPresent()) {
            this.cpu = Optional.ofNullable(cpu);
            return true;
        }else{
            return false;
        }
    }

    public Optional<Hdd> getHdd() {
        return hdd;
    }

    public boolean setHdd(Hdd hdd) {
        if(!this.hdd.isPresent()) {
            this.hdd = Optional.ofNullable(hdd);
            return true;
        }else{
            return false;
        }
    }

    public Optional<Head> getHead() {
        return head;
    }

    public boolean setHead(Head head) {
        if(!this.head.isPresent()) {
            this.head = Optional.ofNullable(head);
            return true;
        }else{
            return false;
        }
    }

    public Optional<LeftHand> getLeftHand() {
        return leftHand;
    }

    public boolean setLeftHand(LeftHand leftHand) {
        if(!this.leftHand.isPresent()) {
            this.leftHand = Optional.ofNullable(leftHand);
            return true;
        }else{
            return false;
        }
    }

    public Optional<LeftLeg> getLeftLeg() {
        return leftLeg;
    }

    public boolean setLeftLeg(LeftLeg leftLeg) {
        if(!this.leftLeg.isPresent()) {
            this.leftLeg = Optional.ofNullable(leftLeg);
            return true;
        }else{
            return false;
        }
    }

    public Optional<Ram> getRam() {
        return ram;
    }

    public boolean setRam(Ram ram) {
        if(!this.ram.isPresent()) {
            this.ram = Optional.ofNullable(ram);
            return true;
        }else{
            return false;
        }
    }

    public Optional<RightHand> getRightHand() {
        return rightHand;
    }

    public boolean setRightHand(RightHand rightHand) {
        if(!this.rightHand.isPresent()) {
            this.rightHand = Optional.ofNullable(rightHand);
            return true;
        }else{
            return false;
        }
    }

    public Optional<RightLeg> getRightLeg() {
        return rightLeg;
    }

    public boolean setRightLeg(RightLeg rightLeg) {
        if(!this.rightLeg.isPresent()) {
            this.rightLeg = Optional.ofNullable(rightLeg);
            return true;
        }else{
            return false;
        }
    }
}
