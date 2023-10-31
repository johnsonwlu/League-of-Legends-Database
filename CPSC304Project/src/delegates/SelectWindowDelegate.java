package delegates;

import java.sql.Connection;

public interface SelectWindowDelegate {
    Connection getConnection();
    public String[] handleFirstQuery();
    public String[] handleSecondQuery();
    public String[] handleThirdQuery();
    public String[] handleFourthQuery();
    public String[] handleFifthQuery();
    public String[] handleSixthQuery();
    public String[] handleSeventhQuery();
    public String[] handleEighthQuery();
    public String[] handleNinthQuery();
    public String[] handleTenthQuery();
    public String[] handleEleventhQuery();
    public String[] handleTwelfthQuery();
}
