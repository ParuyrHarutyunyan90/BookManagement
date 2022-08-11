package books.storage;


import books.exception.AuthorNotFoundException;
import books.model.Author;

public class AuthorStorage {

    private Author[] array = new Author[10];
    private int size = 0;

    public void add(Author value) {
        if (size == array.length) {
            inccreaseArray();
        }
        array[size++] = value;
    }

    public int getSize() {
        return size;
    }

    private void inccreaseArray() {
        Author[] temp = new Author[array.length + 10];
        for (int i = 0; i < size; i++) {
            temp[i] = array[i];
        }
        array = temp;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + " " + array[i] + " ");
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Author getByIndex(int i) throws AuthorNotFoundException {
        if (i >= 0 && i < size) {
            return array[i];
        } else {
            throw new AuthorNotFoundException("Author with" + i + "does not exists");
        }
    }

    public int getFirstIndexByValue(Author value) {
        for (int i = 0; i < size; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;  // -1 քանի որ 0-ն նույնպես մասիվի ինդեքս է
    }

    public void set(int i, Author value) {
        array[i] = value;
    }

    public void add(int index, Author value) {
        if (size + 1 == array.length) {
            inccreaseArray();
        }
        Author temp;
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
            Author temp;
            temp = array[i];
            array[i] = array[i + 1];
            array[i + 1] = temp;
        }
        size--;
    }

//    public void printBookByAuthorName(String authorName) {
//        for (int i = 0; i < size; i++) {
//            if (array[i].getAuthorName().equals(authorName.trim())) {
//                System.out.println(array[i]);
//            }
//
//        }
//    }

//    public void printBookByGenre(String genre) {
//        for (int i = 0; i < size; i++) {
//            if (array[i].getGenre().equals(genre.trim())) {
//                System.out.println(array[i]);
//            }
//
//        }
//    }

//    public void printBookByPriceRange(double min_price, double max_price) {
//        for (int i = 0; i < size; i++) {
//            if (array[i].getPrice() >= min_price && array[i].getPrice() <= max_price) {
//                System.out.println(array[i]);
//            }
//
//        }
//    }
}
