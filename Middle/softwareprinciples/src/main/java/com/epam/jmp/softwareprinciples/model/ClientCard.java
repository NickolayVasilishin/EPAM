package com.epam.jmp.softwareprinciples.model;

/**
 * Created by User on 3/8/2017.
 */
public class ClientCard implements ClientData {
    private String name;
    private String address;

    public ClientCard() {
    }

    @Override
    public String toString() {
        return "ClientCard{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public ClientCard(String s, String separator) {
        String[] values = s.split(separator);
        name = values[0];
        address = values[1];
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientCard that = (ClientCard) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return address != null ? address.equals(that.address) : that.address == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
