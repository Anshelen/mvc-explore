package ru.javastudy.mvc.core.rest.model;

/**
 * Created for JavaStudy.ru on 28.02.2016.
 */
public class RestUserModel {

    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;

    public RestUserModel() {

    }

    public RestUserModel(int id, String name, String username, String email, Address address) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * specification says that inner class must be static. But it work fine without it.
     */
    public class Address {
        private String street;
        private String suite;
        private String zipcode;
        private Geo geo;

        public Address() {}

        public Address(String street, String suite, String zipcode, Geo geo) {
            this.street = street;
            this.suite = suite;
            this.zipcode = zipcode;
            this.geo = geo;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getSuite() {
            return suite;
        }

        public void setSuite(String suite) {
            this.suite = suite;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public Geo getGeo() {
            return geo;
        }

        public void setGeo(Geo geo) {
            this.geo = geo;
        }

        public class Geo {
            private Double lat;
            private Double lng;

            public Geo() {}

            public Geo(Double lat, Double lng) {
                this.lat = lat;
                this.lng = lng;
            }

            public Double getLat() {
                return lat;
            }

            public void setLat(Double lat) {
                this.lat = lat;
            }

            public Double getLng() {
                return lng;
            }

            public void setLng(Double lng) {
                this.lng = lng;
            }
        }
    }
}

