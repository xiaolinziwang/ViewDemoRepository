package com.chunlin.viewdemo.entity;

/**
 * Created by Administrator on 2017/4/11.
 */

public class DetailTimeMachineEntity {

    /**
     * error : 0
     * code : 200
     * message : 成功返回
     * data : {"price_arr":[{"date":20170411,"data":[{"id":"2984667","gov_id":"3156568","source":"3","company_id":"0","now_created":"1491880446","pre_created":"1491880446","source_url":"http://beijing.koofang.com/sale/d-1006278227.html","pre_house_price":"1000.00","now_house_price":"1000.00","house_area":"276","house_room":"5","house_floor":"11","borough_id":"30615","borough_name":"天通苑北一区","cityarea_id":"2","change_type":"0","change_range":"0.0000","change_value":"0.0000","type":"1","cityarea2_id":"210","source_name":"21世纪","desc":"21世纪上架，报价：1000.00"}]},{"date":"20170411","data":[{"house_floor":"11","updated":1.4918804546871E9,"house_built_year":"2003","source_name":"21世纪","gov_id":3156568,"created":"1491880454","house_price":1000,"source_url":"http://beijing.koofang.com/sale/d-1006278227.html","house_fitment":2,"house_toward":9,"tag":"#","house_title":"商满五唯壹天通苑北一区板楼南北带电梯11加12加13复式","source":3,"owner_phone":"13301050897","id":3156568,"owner_name":"马进红","broker_num":0,"service_phone":"","house_desc":"我是21世纪的一名经纪人，竭诚为您服务，这套房子在天通苑北一区。附近有超市商场,天通苑尾货商场,物美,京客隆，华联商场，西单商场，翠微百货，中大电器，龙德紫金,附近有医院,北京天通苑社区门诊部,航空工业中心医院天通苑门诊部,三级甲等医院清华附属医院,附近有学校,幸福泉幼儿园,春天幼儿园,新世纪幼儿园,东方幼儿园；金色童年双语,天骄幼儿园天通苑小学,中山实验中学,昌平一中，中苑小学（建设中）暂无,我保证信息的真实，并期待您和我联系!","source_logo":"http://zhaijiso-pic.b0.upaiyun.com/house/source/571b2d7a974e3.png","desc":"21世纪：报价1000","now_house_price":1000}]}]}
     * page : []
     * totalRunTime : 5.6
     */

    private int error;
    private int code;
    private String message;
    private DataBean data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * date : 20170411
         * data : [{"id":"2984667","gov_id":"3156568","source":"3","company_id":"0","now_created":"1491880446","pre_created":"1491880446","source_url":"http://beijing.koofang.com/sale/d-1006278227.html","pre_house_price":"1000.00","now_house_price":"1000.00","house_area":"276","house_room":"5","house_floor":"11","borough_id":"30615","borough_name":"天通苑北一区","cityarea_id":"2","change_type":"0","change_range":"0.0000","change_value":"0.0000","type":"1","cityarea2_id":"210","source_name":"21世纪","desc":"21世纪上架，报价：1000.00"}]
         */

        private Object price_arr;

        public Object getPrice_arr() {
            return price_arr;
        }

        public void setPrice_arr(Object price_arr) {
            this.price_arr = price_arr;
        }

        public static class PriceArrBean {
            private int date;
            /**
             * id : 2984667
             * gov_id : 3156568
             * source : 3
             * company_id : 0
             * now_created : 1491880446
             * pre_created : 1491880446
             * source_url : http://beijing.koofang.com/sale/d-1006278227.html
             * pre_house_price : 1000.00
             * now_house_price : 1000.00
             * house_area : 276
             * house_room : 5
             * house_floor : 11
             * borough_id : 30615
             * borough_name : 天通苑北一区
             * cityarea_id : 2
             * change_type : 0
             * change_range : 0.0000
             * change_value : 0.0000
             * type : 1
             * cityarea2_id : 210
             * source_name : 21世纪
             * desc : 21世纪上架，报价：1000.00
             */

            private Object data;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public Object getData() {
                return data;
            }

            public void setData(Object data) {
                this.data = data;
            }

            public static class DataBeanTimeMachine {
                private String id;
                private String gov_id;
                private String source;
                private String company_id;
                private String now_created;
                private String pre_created;
                private String source_url;
                private String pre_house_price;
                private String now_house_price;
                private String house_area;
                private String house_room;
                private String house_floor;
                private String borough_id;
                private String borough_name;
                private String cityarea_id;
                private String change_type;
                private String change_range;
                private String change_value;
                private String type;
                private String cityarea2_id;
                private String source_name;
                private String desc;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getGov_id() {
                    return gov_id;
                }

                public void setGov_id(String gov_id) {
                    this.gov_id = gov_id;
                }

                public String getSource() {
                    return source;
                }

                public void setSource(String source) {
                    this.source = source;
                }

                public String getCompany_id() {
                    return company_id;
                }

                public void setCompany_id(String company_id) {
                    this.company_id = company_id;
                }

                public String getNow_created() {
                    return now_created;
                }

                public void setNow_created(String now_created) {
                    this.now_created = now_created;
                }

                public String getPre_created() {
                    return pre_created;
                }

                public void setPre_created(String pre_created) {
                    this.pre_created = pre_created;
                }

                public String getSource_url() {
                    return source_url;
                }

                public void setSource_url(String source_url) {
                    this.source_url = source_url;
                }

                public String getPre_house_price() {
                    return pre_house_price;
                }

                public void setPre_house_price(String pre_house_price) {
                    this.pre_house_price = pre_house_price;
                }

                public String getNow_house_price() {
                    return now_house_price;
                }

                public void setNow_house_price(String now_house_price) {
                    this.now_house_price = now_house_price;
                }

                public String getHouse_area() {
                    return house_area;
                }

                public void setHouse_area(String house_area) {
                    this.house_area = house_area;
                }

                public String getHouse_room() {
                    return house_room;
                }

                public void setHouse_room(String house_room) {
                    this.house_room = house_room;
                }

                public String getHouse_floor() {
                    return house_floor;
                }

                public void setHouse_floor(String house_floor) {
                    this.house_floor = house_floor;
                }

                public String getBorough_id() {
                    return borough_id;
                }

                public void setBorough_id(String borough_id) {
                    this.borough_id = borough_id;
                }

                public String getBorough_name() {
                    return borough_name;
                }

                public void setBorough_name(String borough_name) {
                    this.borough_name = borough_name;
                }

                public String getCityarea_id() {
                    return cityarea_id;
                }

                public void setCityarea_id(String cityarea_id) {
                    this.cityarea_id = cityarea_id;
                }

                public String getChange_type() {
                    return change_type;
                }

                public void setChange_type(String change_type) {
                    this.change_type = change_type;
                }

                public String getChange_range() {
                    return change_range;
                }

                public void setChange_range(String change_range) {
                    this.change_range = change_range;
                }

                public String getChange_value() {
                    return change_value;
                }

                public void setChange_value(String change_value) {
                    this.change_value = change_value;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getCityarea2_id() {
                    return cityarea2_id;
                }

                public void setCityarea2_id(String cityarea2_id) {
                    this.cityarea2_id = cityarea2_id;
                }

                public String getSource_name() {
                    return source_name;
                }

                public void setSource_name(String source_name) {
                    this.source_name = source_name;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }
            }
        }
    }
}
