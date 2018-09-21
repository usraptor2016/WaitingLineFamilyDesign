
package components.waitingline;

import java.util.Comparator;

/**
 * {@code WaitingListKernel} enhanced with secondary methods.
 *
 * @param <T>
 *            type of {@code Queue} entries
 * @mathdefinitions <pre>
 * IS_TOTAL_PREORDER (
 *   r: binary relation on T
 *  ) : boolean is
 *  for all x, y, z: T
 *   ((r(x, y) or r(y, x))  and
 *    (if (r(x, y) and r(y, z)) then r(x, z)))
 *
 * IS_SORTED (
 *   s: string of T,
 *   r: binary relation on T
 *  ) : boolean is
 *  for all x, y: T where (<x, y> is substring of s) (r(x, y))
 * </pre>
 */
public interface WaitingLine<T> extends components.queue.WaitingLine<T> {

    /**
     * Reports the front of {@code this}.
     *
     * @return the front entry of {@code this}
     * @aliases reference returned by {@code front}
     * @requires this /= <>
     * @ensures <front> is prefix of this
     */
    T front();

    /**
     * Concatenates ("appends") {@code q} to the end of {@code this}.
     *
     * @param q
     *            the {@code Queue} to be appended to the end of {@code this}
     * @updates this
     * @clears q
     * @ensures this = #this * #q
     */
    void append(WaitingLine<T> q);

    /**
     * Sorts {@code this} according to the ordering provided by the
     * {@code compare} method from {@code order}.
     *
     * @param order
     *            ordering by which to sort
     * @updates this
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * perms(this, #this)  and
     * IS_SORTED(this, [relation computed by order.compare method])
     * </pre>
     */
    void sort(Comparator<T> order);

    /**
     * Report if {@code element} is in {@code this}.
     *
     * @param element
     *            the element to be checked
     * @return true if and only if element is in {@code this}, false otherwise
     * @ensures contains = (element is in this)
     */
    boolean contains(T element);

    /**
     * Report the position of {@code element} in {@code this}.
     *
     * @param element
     *            the element to be checked
     * @return the position of {@code element} in {@code this}
     * @requires element is in {@code this}
     */
    int position(T element);

    /**
     * Remove the entry at {@code pos} from {@code this}.
     *
     * @param pos
     *            the position of the entry to be removed
     * @return the entry is at {@code pos}
     * @requires pos <= length of {@code this}
     * @ensures the entry at {@code pos} be removed and be returned
     */
    T remove(int pos);
}
