package com.example.demo.practice.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DouBanMovieItem {

    /**
     * count : 1
     * start : 0
     * total : 44
     * subjects : [{"rating":{"max":10,"average":7.9,"details":{"1":21,"3":2705,"2":160,"5":2585,"4":6654},"stars":"40","min":0},"genres":["剧情","犯罪","悬疑"],"title":"骡子","casts":[{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515234371.83.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515234371.83.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515234371.83.jpg"},"name_en":"Clint Eastwood","name":"克林特·伊斯特伍德","alt":"https://movie.douban.com/celebrity/1054436/","id":"1054436"},{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p12956.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p12956.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p12956.jpg"},"name_en":"Bradley Cooper","name":"布莱德利·库珀","alt":"https://movie.douban.com/celebrity/1013757/","id":"1013757"},{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1490098154.3.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1490098154.3.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1490098154.3.jpg"},"name_en":"Laurence Fishburne","name":"劳伦斯·菲什伯恩","alt":"https://movie.douban.com/celebrity/1022596/","id":"1022596"}],"durations":["116分钟","112分钟(中国大陆)"],"collect_count":50628,"mainland_pubdate":"2019-08-26","has_video":false,"original_title":"The Mule","subtype":"movie","directors":[{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515234371.83.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515234371.83.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515234371.83.jpg"},"name_en":"Clint Eastwood","name":"克林特·伊斯特伍德","alt":"https://movie.douban.com/celebrity/1054436/","id":"1054436"}],"pubdates":["2018-12-14(美国)","2019-08-26(中国大陆)"],"year":"2018","images":{"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2563626309.jpg","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2563626309.jpg","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2563626309.jpg"},"alt":"https://movie.douban.com/subject/30135113/","id":"30135113"}]
     * title : 正在上映的电影-广州
     */

    private int count;
    private int start;
    private int total;
    private String title;
    private List<SubjectsBean> subjects;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubjectsBean> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectsBean> subjects) {
        this.subjects = subjects;
    }

    public static class SubjectsBean {
        /**
         * rating : {"max":10,"average":7.9,"details":{"1":21,"3":2705,"2":160,"5":2585,"4":6654},"stars":"40","min":0}
         * genres : ["剧情","犯罪","悬疑"]
         * title : 骡子
         * casts : [{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515234371.83.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515234371.83.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515234371.83.jpg"},"name_en":"Clint Eastwood","name":"克林特·伊斯特伍德","alt":"https://movie.douban.com/celebrity/1054436/","id":"1054436"},{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p12956.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p12956.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p12956.jpg"},"name_en":"Bradley Cooper","name":"布莱德利·库珀","alt":"https://movie.douban.com/celebrity/1013757/","id":"1013757"},{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1490098154.3.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1490098154.3.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1490098154.3.jpg"},"name_en":"Laurence Fishburne","name":"劳伦斯·菲什伯恩","alt":"https://movie.douban.com/celebrity/1022596/","id":"1022596"}]
         * durations : ["116分钟","112分钟(中国大陆)"]
         * collect_count : 50628
         * mainland_pubdate : 2019-08-26
         * has_video : false
         * original_title : The Mule
         * subtype : movie
         * directors : [{"avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515234371.83.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515234371.83.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515234371.83.jpg"},"name_en":"Clint Eastwood","name":"克林特·伊斯特伍德","alt":"https://movie.douban.com/celebrity/1054436/","id":"1054436"}]
         * pubdates : ["2018-12-14(美国)","2019-08-26(中国大陆)"]
         * year : 2018
         * images : {"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2563626309.jpg","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2563626309.jpg","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2563626309.jpg"}
         * alt : https://movie.douban.com/subject/30135113/
         * id : 30135113
         */

        private RatingBean rating;
        private String title;
        private int collect_count;
        private String mainland_pubdate;
        private boolean has_video;
        private String original_title;
        private String subtype;
        private String year;
        private ImagesBean images;
        private String alt;
        private String id;
        private List<String> genres;
        private List<CastsBean> casts;
        private List<String> durations;
        private List<DirectorsBean> directors;
        private List<String> pubdates;

        public RatingBean getRating() {
            return rating;
        }

        public void setRating(RatingBean rating) {
            this.rating = rating;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCollect_count() {
            return collect_count;
        }

        public void setCollect_count(int collect_count) {
            this.collect_count = collect_count;
        }

        public String getMainland_pubdate() {
            return mainland_pubdate;
        }

        public void setMainland_pubdate(String mainland_pubdate) {
            this.mainland_pubdate = mainland_pubdate;
        }

        public boolean isHas_video() {
            return has_video;
        }

        public void setHas_video(boolean has_video) {
            this.has_video = has_video;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public String getSubtype() {
            return subtype;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public ImagesBean getImages() {
            return images;
        }

        public void setImages(ImagesBean images) {
            this.images = images;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<String> getGenres() {
            return genres;
        }

        public void setGenres(List<String> genres) {
            this.genres = genres;
        }

        public List<CastsBean> getCasts() {
            return casts;
        }

        public void setCasts(List<CastsBean> casts) {
            this.casts = casts;
        }

        public List<String> getDurations() {
            return durations;
        }

        public void setDurations(List<String> durations) {
            this.durations = durations;
        }

        public List<DirectorsBean> getDirectors() {
            return directors;
        }

        public void setDirectors(List<DirectorsBean> directors) {
            this.directors = directors;
        }

        public List<String> getPubdates() {
            return pubdates;
        }

        public void setPubdates(List<String> pubdates) {
            this.pubdates = pubdates;
        }

        public static class RatingBean {
            /**
             * max : 10
             * average : 7.9
             * details : {"1":21,"3":2705,"2":160,"5":2585,"4":6654}
             * stars : 40
             * min : 0
             */

            private int max;
            private double average;
            private DetailsBean details;
            private String stars;
            private int min;

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public double getAverage() {
                return average;
            }

            public void setAverage(double average) {
                this.average = average;
            }

            public DetailsBean getDetails() {
                return details;
            }

            public void setDetails(DetailsBean details) {
                this.details = details;
            }

            public String getStars() {
                return stars;
            }

            public void setStars(String stars) {
                this.stars = stars;
            }

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }

            public static class DetailsBean {
                /**
                 * 1 : 21
                 * 3 : 2705
                 * 2 : 160
                 * 5 : 2585
                 * 4 : 6654
                 */

                @SerializedName("1")
                private int _$1;
                @SerializedName("3")
                private int _$3;
                @SerializedName("2")
                private int _$2;
                @SerializedName("5")
                private int _$5;
                @SerializedName("4")
                private int _$4;

                public int get_$1() {
                    return _$1;
                }

                public void set_$1(int _$1) {
                    this._$1 = _$1;
                }

                public int get_$3() {
                    return _$3;
                }

                public void set_$3(int _$3) {
                    this._$3 = _$3;
                }

                public int get_$2() {
                    return _$2;
                }

                public void set_$2(int _$2) {
                    this._$2 = _$2;
                }

                public int get_$5() {
                    return _$5;
                }

                public void set_$5(int _$5) {
                    this._$5 = _$5;
                }

                public int get_$4() {
                    return _$4;
                }

                public void set_$4(int _$4) {
                    this._$4 = _$4;
                }
            }
        }

        public static class ImagesBean {
            /**
             * small : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2563626309.jpg
             * large : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2563626309.jpg
             * medium : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2563626309.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }

        public static class CastsBean {
            /**
             * avatars : {"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515234371.83.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515234371.83.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515234371.83.jpg"}
             * name_en : Clint Eastwood
             * name : 克林特·伊斯特伍德
             * alt : https://movie.douban.com/celebrity/1054436/
             * id : 1054436
             */

            private AvatarsBean avatars;
            private String name_en;
            private String name;
            private String alt;
            private String id;

            public AvatarsBean getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsBean avatars) {
                this.avatars = avatars;
            }

            public String getName_en() {
                return name_en;
            }

            public void setName_en(String name_en) {
                this.name_en = name_en;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public static class AvatarsBean {
                /**
                 * small : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515234371.83.jpg
                 * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515234371.83.jpg
                 * medium : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515234371.83.jpg
                 */

                private String small;
                private String large;
                private String medium;

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }
            }
        }

        public static class DirectorsBean {
            /**
             * avatars : {"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515234371.83.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515234371.83.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515234371.83.jpg"}
             * name_en : Clint Eastwood
             * name : 克林特·伊斯特伍德
             * alt : https://movie.douban.com/celebrity/1054436/
             * id : 1054436
             */

            private AvatarsBeanX avatars;
            private String name_en;
            private String name;
            private String alt;
            private String id;

            public AvatarsBeanX getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsBeanX avatars) {
                this.avatars = avatars;
            }

            public String getName_en() {
                return name_en;
            }

            public void setName_en(String name_en) {
                this.name_en = name_en;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public static class AvatarsBeanX {
                /**
                 * small : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515234371.83.jpg
                 * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515234371.83.jpg
                 * medium : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1515234371.83.jpg
                 */

                private String small;
                private String large;
                private String medium;

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }
            }
        }
    }
}
