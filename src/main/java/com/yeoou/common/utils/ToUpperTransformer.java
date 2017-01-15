package com.yeoou.common.utils;

import org.apache.commons.collections.Transformer;

/**
 * <p>
 * Title: ToUpperTransformer
 * </p>
 * <p>
 * Description: This is useful for performing case-insensitive sorting using the commons package.
 * Here is an example of how this class can be used:
 * <code>
 * Comparator xformComparator = new TransformingComparator(new ToUpperTransformer(), new NullComparator());
 * Comparator comparator = new BeanComparator("firstName", xformComparator);
 * </code>
 * in the above comparator, the firstName property of the bean being compared is transformed to uppercase
 * and nullsafe.
 * The collection's properties are not actually transformed to uppercase - but the comparator does a case-insenstive sorting.
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2008-01-10
 */
public class ToUpperTransformer implements Transformer {

    /**
     * no-arg constructor
     */
    public ToUpperTransformer() {
        super();
    }

    /**
     * converts the incoming string to uppercase
     * @see org.apache.commons.collections.Transformer#transform(java.lang.Object)
     */
    public Object transform( Object arg0 ) {
        if(arg0 instanceof String){
            if(arg0!=null){
                return((String) arg0).toUpperCase();
            }
        }
        return arg0;
    }
}
