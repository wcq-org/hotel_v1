package cn.itcast.dao;

import java.util.List;

import cn.itcast.entity.Food;
import cn.itcast.utils.PageBean;

public interface IFoodDao {

	void add(Food food);
	
	void delete(int id);
	
	void updata(Food food);
	
	List<Food> query();

	Food findById(int id);

	List<Food> query(String keyword);
	
	List<Food> findByType(int type);
	/**
	 * ��ҳ��ѯ����
	 */
	void getAll(PageBean<Food> pb);
	
	
	/**
	 * ��ѯ�ܼ�¼��
	 */

	int getTotalCount(PageBean<Food> pb);
}
