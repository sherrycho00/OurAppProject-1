package com.example.elsie.framelayout.Rank.ServiceRank;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Elsie on 2017/12/15.
 */

public class ServiceDataResult  implements Serializable {

    private List<ResultsBean> results;


    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         *  "_id":1,
         "title":"一楼",
         "workerList":[
         {
         "workerID":101,
         "workerName":"黃阿姨",
         "window":1,
         "score":101,
         "workerImg":"1"
         },
         "type":"Android",
         "desc":""
         */

        private int _id;
        private String title;
        private String type;
        private String desc;
        private List<WorkerListBean> workerList;

        public int get_id() {
            return _id;
        }

        public void set_id(int _id) {
            this._id = _id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public List<WorkerListBean> getWorkerList() {
            return workerList;
        }

        public void setWorkerList(List<WorkerListBean> workerList) {
            this.workerList = workerList;
        }

        public static class WorkerListBean {
            /**
             "workerID":101,
             "workerName":"黃阿姨",
             "window":1,
             "score":101,
             "workerImg":"1"
             */

            private String workerID;
            private String workerName;
            private int window;
            private int  score;
            private String workerImg;

            public String getWorkerID() {
                return workerID;
            }

            public String getWorkerName() {
                return workerName;
            }

            public void setWorkerName(String workerName) {
                this.workerName = workerName;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public int getWindow() {
                return window;
            }

            public void setWindow(int window) {
                this.window = window;
            }

            public String getWorkerImg() {
                return workerImg;
            }

            public void setWorkerImg(String workerImg) {
                this.workerImg = workerImg;
            }

            public void setWorkerID(String workerID) {
                this.workerID = workerID;
            }
        }



    }
}
