package com.example.gps_data_tutorial.common;

public class LocationDistance {
    public static void main(String[] args) {
        Distance distance = distance(37.497943621766865, 127.02759876498632, 37.50887268979235, 127.06312843924901);

        // 마일(Mile) 단위
        System.out.println(distance.getMile()) ;

        // 미터(Meter) 단위
        System.out.println(distance.getMeter()) ;

        // 킬로미터(Kilo Meter) 단위
        System.out.println(distance.getKilometer()) ;


        Location loc1 = new Location(37.497943621766865, 127.02759876498632);
        Location loc2 = new Location(37.50887268979235, 127.06312843924901);
        Distance distance2 = distance(loc1, loc2);

        // 마일(Mile) 단위
        System.out.println(distance2.getMile()) ;

        // 미터(Meter) 단위
        System.out.println(distance2.getMeter()) ;

        // 킬로미터(Kilo Meter) 단위
        System.out.println(distance2.getKilometer()) ;
    }

    private static Distance distance(Location location1, Location location2) {
        return distance(location1.getLat(), location1.getLon(), location2.getLat(), location2.getLon());
    }

    private static Distance distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        double degrees = rad2deg(dist);

        return new Distance(degrees);
    }

    // This function converts decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
