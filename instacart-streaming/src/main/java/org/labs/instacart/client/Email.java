package org.labs.instacart.client;

import java.util.HashSet;
import java.util.Set;

public class Email {
    public static void main(String[] args) {
        Email email = new Email();
        email.numUniqueEmails(new String[] {"aditya.sakhuja@gmail.com",
                                            "aditya.sakhuja+email@gmail.com",
                                            "adityasakhuja@gmail.com"});
    }

    public int numUniqueEmails(String[] emails) {
        if(emails.length == 0 || emails == null ) {
            return 0;
        }
        Set<String> results = new HashSet();
        for(String email : emails) {
            String[] parts = email.split("@");
            StringBuilder sb = new StringBuilder();
            for(char c : parts[0].toCharArray()) {
                if(c != '.') {
                    sb.append(c);
                }
                if(c == '+') {
                    break;
                }
            }
            sb.append("@");
            sb.append(parts[1]);
            results.add(sb.toString());
        }
        return results.size();
    }
}
