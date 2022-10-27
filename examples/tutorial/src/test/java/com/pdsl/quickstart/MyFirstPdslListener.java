package com.pdsl.quickstart;

import com.example.MyFirstParser;
import com.example.MyFirstParserBaseListener;

public class MyFirstPdslListener extends MyFirstParserBaseListener {
    private int numberOfPickles = -1;
    @Override
    public void enterGivenUserHasSpecifiedPickles(MyFirstParser.GivenUserHasSpecifiedPicklesContext ctx) {
        numberOfPickles = Integer.parseInt(ctx.NUMBER().getText());
    }
    @Override
    public void enterThenUserHasMoreThanSpecifiedPickles(MyFirstParser.ThenUserHasMoreThanSpecifiedPicklesContext ctx) {
        assert numberOfPickles > Integer.parseInt(ctx.NUMBER().getText());
        numberOfPickles = Integer.MIN_VALUE;
    }

}
