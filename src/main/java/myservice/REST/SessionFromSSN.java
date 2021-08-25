package myservice.REST;

import java.util.List;

public class SessionFromSSN {
    private final long userSSN;
    private List<String> sessions;

    public SessionFromSSN(Long ssn, List<String> sesh)
    {
        this.userSSN = ssn;
        this.sessions = sesh;
    }

    public long getUserSSN()
    {
        return this.userSSN;
    }

    public List<String> getSessions()
    {
        return this.sessions;
    }
    
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{ </br> User SSN is: " + String.valueOf(userSSN) + "</br>");
        stringBuilder.append("With number of sessions: " + this.sessions.size() + "</br>");
        stringBuilder.append("Session codes: </br>\n");
        for (String s : this.sessions)
        {
            stringBuilder.append("[ " + s + " ] </br>");
        }

        stringBuilder.append("}" + "</br>");

        return stringBuilder.toString();
    }

}
