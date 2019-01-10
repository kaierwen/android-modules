package github.kaierwen.androiddevlibrary.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 包含分类标题，标题底下的Item字符串数据
 *
 * @author zhangky@chinasunfun.com
 * @since 2017/5/18
 */
public class DTO {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title : Widgets
         * list : [{"text":"4.4-KitKat(API 19)"},{"text":"June 2014: Android 4.4W. KitKat for watches(API 20)"},{"text":"November 2014: Lollipop(API 21)"},{"text":"March 2015: Lollipop(API 22)"},{"text":"M is for Marshmallow(API 23)"},{"text":"N is for Nougat(API 24)"}]
         */

        private String title;
        private List<ListBean> list;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Parcelable {
            /**
             * text : 4.4-KitKat(API 19)
             */
            private String text;
            private String url;

            public ListBean() {
            }

            protected ListBean(Parcel in) {
                text = in.readString();
                url = in.readString();
            }

            public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
                @Override
                public ListBean createFromParcel(Parcel in) {
                    return new ListBean(in);
                }

                @Override
                public ListBean[] newArray(int size) {
                    return new ListBean[size];
                }
            };

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(text);
                dest.writeString(url);
            }
        }
    }
}
