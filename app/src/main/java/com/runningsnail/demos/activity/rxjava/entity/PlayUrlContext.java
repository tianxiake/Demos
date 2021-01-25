package com.runningsnail.demos.activity.rxjava.entity;
/**
 * @author lyj on 2021/1/17
 */

/**
 * @author wyb
 */
public class PlayUrlContext {
	private VodDetail vodDetail;
	private AuthDetail authDetail;
	private PlayDetail playDetail;

	public VodDetail getVodDetail() {
		return vodDetail;
	}

	public void setVodDetail(VodDetail vodDetail) {
		this.vodDetail = vodDetail;
	}

	public AuthDetail getAuthDetail() {
		return authDetail;
	}

	public void setAuthDetail(AuthDetail authDetail) {
		this.authDetail = authDetail;
	}

	public PlayDetail getPlayDetail() {
		return playDetail;
	}

	public void setPlayDetail(PlayDetail playDetail) {
		this.playDetail = playDetail;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("PlayUrlContext{");
		sb.append("vodDetail=").append(vodDetail);
		sb.append(", authDetail=").append(authDetail);
		sb.append(", playDetail=").append(playDetail);
		sb.append('}');
		return sb.toString();
	}
}
