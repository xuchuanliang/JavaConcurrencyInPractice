package ConcurrencyPractice.capter04.com.snail.b03;

import ConcurrencyPractice.capter03.anno.ThreadSafe;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 将线程安全委托给多个状态变量
 */
@ThreadSafe
public class VisualComponent {
    private final List<KeyListener> keyListeners = new CopyOnWriteArrayList<>();

    private final List<MouseListener> mouseListeners = new CopyOnWriteArrayList<>();

    public void addKeyListener(KeyListener keyListener){
        keyListeners.add(keyListener);
    }

    public void addMouseListener(MouseListener mouseListener){
        mouseListeners.add(mouseListener);
    }

    public void removeKeyListener(KeyListener keyListener){
        keyListeners.remove(keyListener);
    }

    public void removeMouseListener(MouseListener mouseListener){
        mouseListeners.remove(mouseListener);
    }

}
