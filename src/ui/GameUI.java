package ui;

import rules.Scorer;

public interface GameUI {
    String requestPlayerName(int playernumber);
    Scorer requestRuleSet();
}
