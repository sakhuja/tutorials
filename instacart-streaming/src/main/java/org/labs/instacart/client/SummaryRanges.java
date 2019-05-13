package org.labs.instacart.client;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {
    public static void main(String[] args) {
        SummaryRanges summaryRanges = new SummaryRanges();
        System.out.println(summaryRanges.summaryRanges(new int[] {0,1,2,4,5,7}));
    }

    public List<String> summaryRanges(int[] nums) {
        if(nums == null || nums.length == 0) {return new ArrayList();}
        int start = nums[0];
        int end = nums[0];
        List<String> result = new ArrayList<>();
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - 1 == nums[i - 1]) {
                end = nums[i];
            } else {
                if (start < end) {
                    result.add(start + "->" + end);
                }
                if(start == end) {
                    result.add(Integer.toString(start));
                }
                start = nums[i];
                end = nums[i];
            }
        }
        if (start < end) {
            result.add(start + "->" + end);
        }
        if(start == end) {
            result.add(Integer.toString(start));
        }
        return result;
    }
}
