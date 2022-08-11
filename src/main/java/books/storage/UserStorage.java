package books.storage;

import books.model.User;

public class UserStorage {

    User[] array = new User[10];
    int size = 0;

    public void add(User value) {
        if (size == array.length) {
            increaseArray();
        }
        array[size++] = value;
    }

    private void increaseArray() {
        User[] temp = new User[array.length + 10];
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

    public User getUserByEmail(String email) {
        for (int i = 0; i < size; i++) {
            if (array[i].getEmail().equals(email)) {
                return array[i];
            }
        }
        return null;
    }

    public int getFirstIndexByValue(User value) {
        for (int i = 0; i < size; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;  // -1 քանի որ 0-ն նույնպես մասիվի ինդեքս է
    }

    public void set(int i, User value) {
        array[i] = value;
    }

    public void add(int index, User value) {
        if (size + 1 == array.length) {
            increaseArray();
        }
        User temp;
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
            User temp;
            temp = array[i];
            array[i] = array[i + 1];
            array[i + 1] = temp;
        }
        size--;
    }


}
