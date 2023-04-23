package com.example.welp;

import android.net.Uri;
import android.provider.BaseColumns;

public final class UsersContract {
    // Prevent accidental instantiation
    private UsersContract() {}

    // Authority of the content provider
    public static final String AUTHORITY = "com.example.welp.provider";

    // Base URI of the content provider
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    // Path for the "users" directory
    public static final String PATH_USERS = "users";

    // Inner class that defines the schema of the "users" table
    public static final class User implements BaseColumns {
        // Prevent accidental instantiation
        private User() {}

        // The content URI for the "users" table
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_USERS);

        // The MIME type for a directory of users
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + AUTHORITY + "." + PATH_USERS;

        // The MIME type for a single user
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + AUTHORITY + "." + PATH_USERS;

        // The name of the "users" table
        public static final String TABLE_NAME = "users";

        // The name of the "name" column
        public static final String COLUMN_NAME_NAME = "name";

        // The name of the "email" column
        public static final String COLUMN_NAME_EMAIL = "email";

        // The name of the "password" column
        public static final String COLUMN_NAME_PASSWORD = "password"; } }