package com.example.elsie.framelayout.Rank.ServiceRank;

import java.util.Comparator;

/**
 * Created by Elsie on 2017/12/15.
 */

public class ServiceComparator implements Comparator<WorkerInfo> {
@Override
public int compare(WorkerInfo o1, WorkerInfo o2) {
        int flag = o2.getScore() - o1.getScore();
        if (flag > 0) {
        return 1;
        }
        if (flag < 0) {
        return -1;
        }
        return flag;
        }

}