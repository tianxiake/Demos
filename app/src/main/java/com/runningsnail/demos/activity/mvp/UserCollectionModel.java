package com.runningsnail.demos.activity.mvp;

/**
 * @author yongjie created on 2019/3/30.
 */
public class UserCollectionModel {

	public interface UserCollectionListener {
		void onSuccess();

		void onFailture();
	}


	public void addCollection(CollectionMessageBean collectionMessageBean, UserCollectionListener userCollectionListener) {

	}

	public void queryAllCollection(UserCollectionListener userCollectionListener) {

	}

	public void deleteCollection(UserCollectionListener userCollectionListener) {

	}

	public void deleteAllCollection(UserCollectionListener userCollectionListener) {

	}
}
