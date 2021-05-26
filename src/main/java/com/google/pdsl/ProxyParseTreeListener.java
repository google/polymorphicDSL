package com.google.pdsl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.antlr.v4.runtime.ParserRuleContext;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * Instances of this class allows multiple parse tree listeners to receive events.
 *
 * <b>Note that a ProxyParseTreeListener cannot be used directly by a parse tree walker!</b>
 * It can instead be used via composition in the other ParseTreeListeners implementations that contain the actual
 * grammar rules, which will not be seen or triggered if it is used directly.
 */
public class ProxyParseTreeListener implements ParseTreeListener {
    private List<ParseTreeListener> listeners;

    /**
     * Creates a new proxy without an empty list of listeners. Add
     * listeners before walking the tree.
     */
    public ProxyParseTreeListener() {
        // Setting the listener to null automatically instantiates a new list.
        this( null );
    }

    /**
     * Creates a new proxy with the given list of listeners.
     *
     * @param listeners A list of listeners to receive events.
     */
    public ProxyParseTreeListener( List<ParseTreeListener> listeners ) {
        this.listeners = new LinkedList<>(listeners);
    }

    @Override
    public void enterEveryRule( ParserRuleContext ctx ) {
        for( ParseTreeListener listener : getListeners() ) {
            listener.enterEveryRule( ctx );
            //ctx.enterRule( listener );
        }
    }

    @Override
    public void exitEveryRule( ParserRuleContext ctx ) {
        for( ParseTreeListener listener : getListeners() ) {
            //ctx.exitRule( listener );
            listener.exitEveryRule( ctx );
        }
     }

    @Override
    public void visitErrorNode( ErrorNode node ) {
        for( ParseTreeListener listener : getListeners() ) {
            listener.visitErrorNode( node );
        }
    }

    @Override
    public void visitTerminal( TerminalNode node ) {
        for( ParseTreeListener listener : getListeners() ) {
            listener.visitTerminal( node );
        }
    }

    /**
     * Adds the given listener to the list of event notification recipients.
     *
     * @param listener A listener to begin receiving events.
     */
    public void add( ParseTreeListener listener ) {
        //  Stack overflow exception will occur if this class contains another PorxyParseTreeListener
        if (listener.equals(this)) {
            throw new IllegalArgumentException("A ProxyParseTreeListener cannot contain itself or a stack overflow will occur!");
        }
        getListeners().add( listener );
    }

    /**
     * Removes the given listener to the list of event notification recipients.
     *
     * @param listener A listener to stop receiving events.
     * @return false The listener was not registered to receive events.
     */
    public boolean remove( ParseTreeListener listener ) {
        return getListeners().remove( listener );
    }

    /**
     * Returns the list of listeners.
     *
     * @return The list of listeners to receive tree walking events.
     */
    private List<ParseTreeListener> getListeners() {
        return this.listeners;
    }

    /**
     * Changes the list of listeners to receive events. If the given list of
     * listeners is null, an empty list will be created.
     *
     * @param listeners A list of listeners to receive tree walking
     * events.
     */
    /*
    public void setListeners( List<ParseTreeListener> listeners ) {
        if( listeners == null ) {
            listeners = createParseTreeListenerList();
        }

        this.listeners = listeners;
    }
     */

    /**
     * Creates a CopyOnWriteArrayList to permit concurrent mutative
     * operations.
     *
     * @return A thread-safe, mutable list of event listeners.
     */
    protected List<ParseTreeListener> createParseTreeListenerList() {
        return new CopyOnWriteArrayList<ParseTreeListener>();
    }
}
