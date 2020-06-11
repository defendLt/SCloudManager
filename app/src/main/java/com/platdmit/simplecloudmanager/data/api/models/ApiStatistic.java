package com.platdmit.simplecloudmanager.data.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiStatistic {
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("RAM")
    @Expose
    private String ram;
    @SerializedName("CPU")
    @Expose
    private String cpu;
    @SerializedName("disk")
    @Expose
    private ApiDisk disk;
    @SerializedName("traffic")
    @Expose
    private ApiTraffic traffic;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public ApiDisk getDisk() {
        return disk;
    }

    public void setDisk(ApiDisk disk) {
        this.disk = disk;
    }

    public ApiTraffic getTraffic() {
        return traffic;
    }

    public void setTraffic(ApiTraffic traffic) {
        this.traffic = traffic;
    }

    public class ApiDisk {
        @SerializedName("write")
        @Expose
        private String write;
        @SerializedName("read")
        @Expose
        private String read;

        public String getWrite() {
            return write;
        }

        public void setWrite(String write) {
            this.write = write;
        }

        public String getRead() {
            return read;
        }

        public void setRead(String read) {
            this.read = read;
        }
    }

    public class ApiTraffic {
        @SerializedName("incoming")
        @Expose
        private String incoming;
        @SerializedName("outgoing")
        @Expose
        private String outgoing;

        public String getIncoming() {
            return incoming;
        }

        public void setIncoming(String incoming) {
            this.incoming = incoming;
        }

        public String getOutgoing() {
            return outgoing;
        }

        public void setOutgoing(String outgoing) {
            this.outgoing = outgoing;
        }
    }
}
