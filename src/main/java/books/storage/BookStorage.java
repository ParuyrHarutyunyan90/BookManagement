package books.storage;

import books.model.Book;

public class BookStorage {

    Book[] array = new Book[10];
    int size = 0;

    public void add(Book value) {
        if (size == array.length) {
            increaseArray();
        }
        array[size++] = value;
    }

    private void increaseArray() {
        Book[] temp = new Book[array.length + 10];
        if (size >= 0) System.arraycopy(array, 0, temp, 0, size);
        array = temp;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(array[i] + " ");
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    Book detByIndex(int i) {
        return array[i - 1];
    }

    public int getFirstIndexByValue(Book value) {
        for (int i = 0; i < size; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;  // -1 քանի որ 0-ն նույնպես մասիվի ինդեքս է
    }

    public void set(int i, Book value) {
        array[i] = value;
    }

    public void add(int index, Book value) {
        if (size + 1 == array.length) {
            increaseArray();
        }
        Book temp;
        for (int i = size; i >= index; i--) {
            temp = array[i];
            array[i + 1] = temp;
            array[i + 1] = array[i];
        }
        size++;
        array[index] = value;
    }

    public void delete(int index) {
        for (int i = index; i < size; i++) {
            Book temp;
            temp = array[i];
            array[i] = array[i + 1];
            array[i + 1] = temp;
        }
        size--;
    }

    public void printBookByAuthorName(String authorName) {
        for (int i = 0; i < size; i++) {
            if (array[i].getAuthorName().equals(authorName.trim())) {
                System.out.println(array[i]);
            }

        }
    }

    public void printBookByGenre(String genre) {
        for (int i = 0; i < size; i++) {
            if (array[i].getGenre().equals(genre.trim())) {
                System.out.println(array[i]);
            }

        }
    }

    public void printBookByPriceRange(double min_price, double max_price) {
        for (int i = 0; i < size; i++) {
            if (array[i].getPrice() >= min_price && array[i].getPrice() <= max_price) {
                System.out.println(array[i]);
            }

        }
    }
}
