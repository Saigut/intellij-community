package com.siyeh.ipp.psiutils;

import com.intellij.psi.PsiBinaryExpression;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiJavaToken;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ComparisonUtils{
    private static final Set<String> s_comparisonStrings = new HashSet<String>(6);
    private static final Map<String,String> s_swappedComparisons = new HashMap<String, String>(6);
    private static final Map<String,String> s_invertedComparisons = new HashMap<String, String>(6);

    private ComparisonUtils(){
        super();
    }

    static{
        s_comparisonStrings.add("==");
        s_comparisonStrings.add("!=");
        s_comparisonStrings.add(">");
        s_comparisonStrings.add("<");
        s_comparisonStrings.add(">=");
        s_comparisonStrings.add("<=");

        s_swappedComparisons.put("==", "==");
        s_swappedComparisons.put("!=", "!=");
        s_swappedComparisons.put(">", "<");
        s_swappedComparisons.put("<", ">");
        s_swappedComparisons.put(">=", "<=");
        s_swappedComparisons.put("<=", ">=");

        s_invertedComparisons.put("==", "!=");
        s_invertedComparisons.put("!=", "==");
        s_invertedComparisons.put(">", "<=");
        s_invertedComparisons.put("<", ">=");
        s_invertedComparisons.put(">=", "<");
        s_invertedComparisons.put("<=", ">");
    }

    public static boolean isComparison(PsiExpression exp){
        if(!(exp instanceof PsiBinaryExpression)){
            return false;
        }
        final PsiBinaryExpression binaryExpression = (PsiBinaryExpression) exp;
        final PsiJavaToken sign = binaryExpression.getOperationSign();
        final String operation = sign.getText();
        return s_comparisonStrings.contains(operation);
    }

    public static String getFlippedComparison(String str){
        return s_swappedComparisons.get(str);
    }

    public static String getNegatedComparison(String str){
        return s_invertedComparisons.get(str);
    }
}
