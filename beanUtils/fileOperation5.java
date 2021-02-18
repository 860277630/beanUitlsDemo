package com.example.demo.fileLoad;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.example.demo.entity.BookOwner;
import com.example.demo.entity.Books;
import com.example.demo.entity.HaveBooks;
import com.example.demo.entity.HavePapers;
import com.example.demo.entity.Paper;
import com.example.demo.utils.MD5Util;
import com.example.demo.utils.UUIDUtils;
import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

public class fileOperation5 {

	// for循环控制迭代器
	private static List<String> lists = new ArrayList<>();

	private static LinkedHashMap<String, String> linkMap = new LinkedHashMap<>();

	private static List<Books> staticBooks = new ArrayList<>();
	static {
		String[] list = { "zhangsan", "lisi", "wangwu", "zhaoliu" };
		lists = Arrays.asList(list);

		linkMap.put("zhangsan", "第一名");
		linkMap.put("lisi", "第二名");
		linkMap.put("wangwu", "第三名");
		linkMap.put("zhaoliu", "第四名");
		linkMap.put("田七", "第五名");
		linkMap.put("laoba", "第六名");

		staticBooks.add(new Books(1, "aaa", 69.5, 55, 1));
		staticBooks.add(new Books(2, "bbb", 68.5, 45, 2));
		staticBooks.add(new Books(3, "ccc", 67.5, 85, 6));
		staticBooks.add(new Books(4, "ddd", 66.5, 25, 5));
		staticBooks.add(new Books(5, "eee", 65.5, 65, 9));
	}

	// 测试list的引用
	@Test
	public void testListOfQuote() {
		List<Books> books = new ArrayList<>();
		books.add(new Books(1, "22"));
		books.add(new Books(2, "33"));
		books.add(new Books(3, "44"));
		books.add(new Books(4, "55"));
		books.add(new Books(5, "66"));

		List<Books> books2 = new ArrayList<>();
		books2.addAll(books);
		// 然后改变新的list里面的值
		for (Books str : books2) {
			str = null;
		}
		System.out.println(books.toString());
	}

	@Test
	public void testyin() {
		Integer integer1 = 1;
		Integer integer2 = integer1;
		integer1++;
		System.out.println("===============" + integer2 + "==================");

	}

	// 得到列表的最后一个元素 并进行更改
	@Test
	public void lastLIst() {
		List<Books> books = new ArrayList<>();
		books.add(new Books(1, "22"));
		books.add(new Books(2, "33"));
		books.add(new Books(3, "44"));
		books.add(new Books(4, "55"));
		books.add(new Books(5, "66"));
		Books books2 = books.get(books.size() - 1);
		books2.setBookname("77");
		System.out.println(books.toString());
	}

	// 重写BeanUtils，不拷贝空值到对象
	@Test
	public void testUtils() {
		Books formbook = new Books(null, null, 35.8, 1598, 8);
		Books tobook = new Books(2, "喜马拉雅");
		BeanUtils.copyProperties(formbook, tobook, getNullPropertyNames(formbook));
		System.out.println(tobook.toString());
	}

	// 满足这个utils的条件是源文件只要不为空的值就会到目标文件进行覆盖
	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null) {
				emptyNames.add(pd.getName());
			}
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	// 传值传引用问题
	@Test
	public void passValue() throws IllegalAccessException, InvocationTargetException {
		List<Books> bookes = new ArrayList<>();
		List<Books> bookes2 = new ArrayList<>();
		List<Books> bookes3 = new ArrayList<>();
		bookes.add(new Books(1, "aaa", 69.5, 55, 1));
		bookes.add(new Books(2, "bbb", 68.5, 45, 2));
		bookes.add(new Books(3, "ccc", 67.5, 85, 6));
		bookes.add(new Books(4, "ddd", 66.5, 25, 5));
		bookes.add(new Books(5, "eee", 65.5, 65, 9));
		bookes2.add(new Books(11, "qq"));
		bookes2.add(new Books(12, "ww"));
		bookes2.add(new Books(13, "ee"));
		bookes2.add(new Books(14, "rr"));
		bookes2.add(new Books(15, "tt"));
		for (Books k2 : bookes2) {
			for (Books k1 : bookes) {
				BeanUtils.copyProperties(k1, k2);
				k2.setBookname(UUIDUtils.getID());
				Books k3 = new Books();
				BeanUtils.copyProperties(k1, k3);
				// k3.setId(10000);
				bookes3.add(k3);
			}
		}
		for (Books k1 : bookes3) {
			System.out.println("=======bookes3========" + k1.toString());
		}
		for (Books k1 : bookes2) {
			System.out.println("=======bookes2========" + k1.toString());
		}
		for (Books k1 : bookes) {
			System.out.println("=======bookes========" + k1.toString());
		}

		// 测试实体
		List<Books> bookes4 = new ArrayList<>();
		bookes4.add(new Books(1, "aaa", 69.5, 55, 1));
		bookes4.add(new Books(2, "bbb", 68.5, 45, 2));
		bookes4.add(new Books(3, "ccc", 67.5, 85, 6));
		bookes4.add(new Books(4, "ddd", 66.5, 25, 5));
		bookes4.add(new Books(5, "eee", 65.5, 65, 9));

		Books b1 = bookes4.get(0);
		b1.setId(100);
		b1.setBookname("张三");
		System.out.println("=========b1======" + bookes4.get(0).toString());

		Books b2 = new Books();
		b2 = bookes4.get(1);
		b2.setId(100);
		b2.setBookname("张三");
		System.out.println("=========b2=========" + bookes4.get(1).toString());

		
		//另一种写法
		Books b3 = staticBooks.get(0);
		b3.setId(100);
		b3.setBookname("张三");
		System.out.println("=========b3======" + staticBooks.get(0).toString());

		Books b4 = new Books();
		b4 = staticBooks.get(1);
		b4.setId(100);
		b4.setBookname("张三");
		System.out.println("=========b4=========" + staticBooks.get(1).toString());

		Books b5 = new Books();
		org.springframework.beans.BeanUtils.copyProperties(staticBooks.get(2), b5);
		b5.setId(100);
		b5.setBookname("张三");
		int typeid3 = b5.getTypeid();
		typeid3 = 999;
		System.out.println("=========b5=========" + staticBooks.get(2).toString());

		Books b6 = new Books();
		org.apache.commons.beanutils.BeanUtils.copyProperties(b6, staticBooks.get(3));
		b6.setId(100);
		b6.setBookname("张三");
		System.out.println("=========b6=========" + staticBooks.get(3).toString());

		// 内联测试
		HaveBooks xiaowang = new HaveBooks(UUIDUtils.getID(), new Books(1, "aaa", 69.5, 55, 1));
		HaveBooks xiaoli = new HaveBooks();
		HaveBooks xiaoliu = new HaveBooks();
		org.springframework.beans.BeanUtils.copyProperties(xiaowang, xiaoli);
		org.apache.commons.beanutils.BeanUtils.copyProperties(xiaoliu, xiaowang);
		System.out.println("=========复制后=========" + xiaoli.toString());
		System.out.println("=========复制后=========" + xiaoliu.toString());
		//一种即使是浅复制也不会影响引用的改法
		
		//修改非内联部分，即非嵌套实体的部分，不会影响引用部分的改变，
		//非内联部分指的是基本类型和不可变类，不包括可变类（非final修饰的，如：stringBuilder，自定义的user类）
		//而且对于基本类型和不可变类，不能用get获得然后等于一个新值，因为这个创建的是新值，并没有改变引用的值
		
		//修改内联部分，有set方法的，可以使用set来改变内联部分的值不会影响
		//get方法会改变引用部分的值，不建议使用get方法来改变值
		//封装好的类的修改值的方法也会影响引用的值，如stringBuilder的append方法
		//而且并不能用get获取，然后等于一个new值，因为这个是重新分配了内存，并没有改变引用的值
		//具体原因还需要进一步探究
		
		//改target查source
		
		//1.演示错误的修改方法    这个可以存jvm角度去分析   具体看本人的引用文章	
		String uuid1 = xiaoli.getUUID();
		uuid1 = "20210217-01";//这样并不能改变xiaoli的UUID，反而创建了一个新的UUID
		Books bks1 = xiaoli.getBooks();
		bks1 = new Books(3,"鲁宾孙漂流鸡",35.66,80,55);//这样并不能改变xiaoli的books信息
		String uuid2 = xiaoliu.getUUID();
		Books bks2 = xiaoliu.getBooks();
		bks2 = new Books(3,"鲁宾孙漂流鸭",35.66,80,55);//这样并不能改变xiaoliu的books信息
		uuid2 = "202102017-02";//这样并不能改变xiaoliu的UUID，反而创建了一个新的UUID
		System.out.println("=======errorChange========="+xiaoli.toString());
		System.out.println("=======errorChange========="+xiaoliu.toString());
		System.out.println("=======errorChange========="+xiaowang.toString());
		
		
		//2.浅复制修改非内联部分  内联部分而不影响引用的值
		//1的演示并没有改变xiaoliu和xiaoli的值，所以可以直接用
		xiaoli.setUUID("20210217-01");
		Books bks3 = new Books(3,"鲁宾孙漂流鸡",35.66,80,55);
		xiaoli.setBooks(bks3);
		xiaoliu.setUUID("202102017-02");
		
		BookOwner bookOwner1 = new BookOwner("小王",23,new StringBuilder("男"),new Books(1, "红楼梦", 69.5, 55, 1));		
		BookOwner bookOwner2 = new BookOwner();		
		BookOwner bookOwner3 = new BookOwner();
		org.springframework.beans.BeanUtils.copyProperties(bookOwner1, bookOwner2);
		org.apache.commons.beanutils.BeanUtils.copyProperties(bookOwner3, bookOwner1);
		System.out.println("=========复制后=========" + bookOwner1.toString());
		System.out.println("=========复制后=========" + bookOwner2.toString());
		System.out.println("=========复制后=========" + bookOwner3.toString());
		//一种即使是浅复制也不会影响引用的改法
		
		//修改非内联部分，即非嵌套实体的部分，不会影响引用部分的改变，
		//非内联部分指的是基本类型和不可变类，不包括可变类（非final修饰的，如：stringBuilder，自定义的user类）
		//而且对于基本类型和不可变类，不能用get获得然后等于一个新值，因为这个创建的是新值，并没有改变引用的值
		
		//修改内联部分，有set方法的，可以使用set来改变内联部分的值不会影响
		//get方法会改变引用部分的值，不建议使用get方法来改变值
		//封装好的类的修改值的方法也会影响引用的值，如stringBuilder的append方法
		//而且并不能用get获取，然后等于一个new值，因为这个是重新分配了内存，并没有改变引用的值
		//具体原因还需要进一步探究
		
		//改target查source
		
		//1.演示错误的修改方法    这个可以从jvm角度去分析   具体看本人的引用文章	
		String name1 = bookOwner2.getName();
		name1 = "小李";//无效的修改方式，JVM中创建了一个新对象
		int age1 = bookOwner2.getAge();
		age1 = 25;//无效的修改方式，JVM中创建了一个新对象
		StringBuilder sex1 = bookOwner2.getSex();//获取到原先的引用，但是指向了新值
		sex1 = new StringBuilder("女");//无效的修改方式，JVM中创建了一个新对象
		Books books6 = bookOwner2.getBooks();//获取到原先的引用，但是指向了新值
		books6 =  new Books(1, "三国演义", 69.5, 55, 1);//无效的修改方式，JVM中创建了一个新对象	
		
		String name2 = bookOwner3.getName();
		name2 = "小李";//无效的修改方式，JVM中创建了一个新对象
		int age2 = bookOwner3.getAge();
		age2 = 25;//无效的修改方式，JVM中创建了一个新对象
		StringBuilder sex2 = bookOwner3.getSex();//获取到原先的引用，但是指向了新值
		sex2 = new StringBuilder("女");//无效的修改方式，JVM中创建了一个新对象
		Books books7 = bookOwner3.getBooks();//获取到原先的引用，但是指向了新值
		books7 =  new Books(1, "水浒传", 69.5, 55, 1);//无效的修改方式，JVM中创建了一个新对象
		System.out.println("=========错误的修改-无效修改=========" + bookOwner1.toString());
		System.out.println("=========错误的修改-无效修改=========" + bookOwner2.toString());
		System.out.println("=========错误的修改-无效修改=========" + bookOwner3.toString());
		
		//2.浅复制修改非内联部分  内联部分而不影响引用的值
		//1的演示并没有改变xiaoliu和xiaoli的值，所以可以直接用
		//用封装好的set方法不会改变引用的值
		bookOwner2.setName("小李");
		bookOwner2.setAge(25);
		bookOwner2.setSex(new StringBuilder("女"));
		bookOwner2.setBooks(new Books(1, "水浒传", 69.5, 55, 1));
		bookOwner3.setName("小李");
		bookOwner3.setAge(25);
		bookOwner3.setSex(new StringBuilder("女"));
		bookOwner3.setBooks(new Books(1, "水浒传", 69.5, 55, 1));
		System.out.println("=========不改变引用的修改=========" + bookOwner1.toString());
		System.out.println("=========不改变引用的修改=========" + bookOwner2.toString());
		System.out.println("=========不改变引用的修改=========" + bookOwner3.toString());
		
		//3.影响引用的修改方式
		//浅复制  除 等于“=”外  非内联的基本类型和不可变类，是不会影响引用的 
		//此处只针对beanUtils，具体请参考本人的深复制浅复制文章
		//因为上面已经对bookOwner2 和  bookOwner3的值进行了修改  所以重新做数据
		BookOwner bookOwner4 = new BookOwner();
		BookOwner bookOwner5 = new BookOwner();
		org.springframework.beans.BeanUtils.copyProperties(bookOwner1, bookOwner4);
		org.apache.commons.beanutils.BeanUtils.copyProperties(bookOwner5, bookOwner1);
		System.out.println("=========复制后-影响引用测试=========" + bookOwner1.toString());
		System.out.println("=========复制后-影响引用测试=========" + bookOwner4.toString());
		System.out.println("=========复制后-影响引用测试=========" + bookOwner5.toString());
		StringBuilder sex4 = bookOwner4.getSex();//用get的方法，就会获取到引用
		sex4.append("性");//改变了引用的值
		Books books8 = bookOwner4.getBooks();//用get的方法，就会获取到引用
		books8.setBookname("水浒传");//改变了引用的值
		StringBuilder sex5 = bookOwner5.getSex();//用get的方法，就会获取到引用
		sex5.append("性");//改变了引用的值
		Books books9 = bookOwner5.getBooks();//用get的方法，就会获取到引用
		books9.setPrices(9999.99);//改变了引用的值
		//从结果可以看到，后果还是很严重的如果不注意
		System.out.println("=========错误的修改-影响引用修改=========" + bookOwner1.toString());
		System.out.println("=========错误的修改-影响引用修改=========" + bookOwner4.toString());
		System.out.println("=========错误的修改-影响引用修改=========" + bookOwner5.toString());
		
		//
		Books books4 = xiaoli.getBooks();
		Books books5 = xiaoliu.getBooks();
		books4.setBookname("梵高日记");
		books5.setPrices(99.99);
		System.out.println("=========xiaowang=========="+xiaowang.toString());

		
		//浅继承  
		Books dictory = new Books(2, "bbb", 68.5, 45, 2);
		Books dictory1 = new Books();
		Books dictory2 = new Books();
		Paper paper = new Paper();
		Paper paper1 = new Paper();
		Paper paper2 = new Paper(0.22,2, "bbb", 68.5, 45, 2);
		Paper paper3 = new Paper();
		Paper paper4 = new Paper();
		
		//父到子
		org.springframework.beans.BeanUtils.copyProperties(dictory, paper);
		org.apache.commons.beanutils.BeanUtils.copyProperties(paper1,dictory);
		System.out.println("==paper==" +paper.toString());
		System.out.println("==paper1==" +paper1.toString());
		System.out.println("==dictory==" +dictory.toString());
		
		
		//子到父
		org.springframework.beans.BeanUtils.copyProperties(paper2, dictory1);
		org.apache.commons.beanutils.BeanUtils.copyProperties(dictory2,paper2);
		System.out.println("==dictory1=="+dictory1.toString());
		System.out.println("==dictory2=="+dictory2.toString());
		System.out.println("==paper2=="+paper2.toString());
		
		
		//子类之间
		org.springframework.beans.BeanUtils.copyProperties(paper2, paper3);
		org.apache.commons.beanutils.BeanUtils.copyProperties(paper4, paper2);
		System.out.println("==paper3=="+paper3.toString());
		System.out.println("==paper4=="+paper4.toString());
		System.out.println("==paper2=="+paper2.toString());
		
		
		
		//   
		HaveBooks B1 = new HaveBooks("123456",2,"钢铁是怎样炼成的",25.66,50,5);
		HavePapers P1 = new HavePapers();
		HavePapers P2 = new HavePapers();
		//父到子
		org.springframework.beans.BeanUtils.copyProperties(B1, P1);
		org.apache.commons.beanutils.BeanUtils.copyProperties(P2, B1);
		System.out.println("==========P1==========="+P1.toString());
		System.out.println("==========P2==========="+P2.toString());
		//改子查父
		P1.setBooks(new Books(3,"鲁宾孙漂流记",99.99,99,9));
		P2.setBooks(new Books(3,"鲁宾孙漂流记",99.99,99,9));
		System.out.println("===========B1============="+B1.toString());
		
		
		//上述方法
		HaveBooks B2 = new HaveBooks();
		HavePapers P3 = new HavePapers();
		HavePapers P4 = new HavePapers();
		B2.setUUID("qwertyui");
		Books books1 = new Books();
		books1.setBookname("钢铁是怎样炼成的");
		B2.setBooks(books1);
		System.out.println("===========beaforeCopy================="+B2.toString());
		//父到子
		org.springframework.beans.BeanUtils.copyProperties(B2, P3);
		org.apache.commons.beanutils.BeanUtils.copyProperties(P4, B2);
		System.out.println("==========P3==========="+P3.toString());
		System.out.println("==========P4==========="+P4.toString());
		//改子查父
		
		//首先是一种无效的改法
		Books books = new Books();
		books.setBookname("骆驼祥子");
		P3.setBooks(books);
		System.out.println("==========afterCopyNouse============="+P3.toString());
		System.out.println("==========afterCopyNouse============="+B2.toString());
		
		
		//然后是有效的改法
		Books books2 = P3.getBooks();
		books2.setBookname("鲁宾孙漂流记");
		System.out.println("===========afterCopy================"+B2.toString());
		Books books3 = new Books();
		books3 = P4.getBooks();
		books3.setBookname("格列佛游记");
		System.out.println("===========afterCopy================"+B2.toString());
		

	}

	// 基于时间的UUID 这样的话即使重启JVM也不会 可以精确到毫微秒
	@Test
	public void uuidTest() {
		// 最普遍的UUID 但是可能会重复
		// version=4
		System.out.println(UUID.randomUUID().version());
		// 32位bd22291e39eb4736a55991497c6376d2
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		// 基于时间的UUID 重复概率会很小 最好在该方法前加事务
		TimeBasedGenerator gen = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
		UUID uuid = gen.generate();
		// version=1
		System.out.println(uuid.version());
		// 32位33304096c01311e58c16089e01cd1de9
		System.out.println(uuid.toString().replaceAll("-", ""));

		try {
			System.out.println(MD5Util.md5Encode(UUID.randomUUID().toString() + gen.generate().toString()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 测试BeanUtils传值传引用问题
	@Test
	public void testBeantils() throws IllegalAccessException, InvocationTargetException {

		// 不用BeanUtils来赋值
		Books b1 = new Books(1, "aaa", 69.5, 55, 1);
		Books b2 = b1;
		b2.setBookname("bbbb");
		System.out.println("========b1==========" + b1.toString());

		Books d1 = new Books(1, "aaa", 69.5, 55, 1);
		Books d2 = new Books();
		d2 = d1;
		d2.setBookname("bbbb");
		System.out.println("========d1==========" + d1.toString());

		// 分别测试两个包内的深拷贝还是浅拷贝

		// spring的BeanUtils source 和target正好相反
		Books b3 = new Books(1, "aaa", 69.5, 55, 1);
		Books b32 = new Books(2, "bbb", 79.5, 66, 2);
		Books b33 = new Books();
		Books b4 = b32;
		BeanUtils.copyProperties(b3, b4);
		BeanUtils.copyProperties(b3, b33);
		// 修改b2的值，看是否影响原数据b1的值
		b4.setBookname(UUID.randomUUID().toString());
		System.out.println("=====b3=====" + b3.toString());
		System.out.println("=====b32=====" + b32.toString());
		System.out.println("======b4====" + b4.toString());
		// 改变b33的值
		b33.setBookname(UUID.randomUUID().toString());
		System.out.println("=====b3=====" + b3.toString());

		// commons的BeanUtils
		Books b5 = new Books(1, "aaa", 69.5, 55, 1);
		Books b52 = new Books(2, "bbb", 79.5, 66, 2);
		Books b53 = new Books();
		Books b6 = b52;
		org.apache.commons.beanutils.BeanUtils.copyProperties(b6, b5);
		org.apache.commons.beanutils.BeanUtils.copyProperties(b53, b5);
		// 修改b4的值，看是否影响原数据b3的值
		b6.setBookname(UUID.randomUUID().toString());
		System.out.println("====b5======" + b5.toString());
		System.out.println("====b52======" + b52.toString());
		System.out.println("=====b6=====" + b6.toString());
		// 改变b33的值
		b53.setBookname(UUID.randomUUID().toString());
		System.out.println("=====b5=====" + b5.toString());
		
		
		
		//两个包的演示
		Books source = new Books(1, "aaa", 69.5, 55, 1);
		Books target1 = new Books();
		Books target2 = new Books();
		
		org.springframework.beans.BeanUtils.copyProperties(source, target1);
		org.apache.commons.beanutils.BeanUtils.copyProperties(target2, source);

		System.out.println("====target1====="+target1.toString());
		System.out.println("====target2====="+target2.toString());

	}
	
	
	@Test
	public void problemsOfGetAndSet () throws IllegalAccessException, InvocationTargetException {
		//Q1:get无法改变非引用的数据值
		
		//test1
		HaveBooks B1 = new HaveBooks("123456",2,"钢铁是怎样炼成的",25.66,50,5);
		String uuid = B1.getUUID();
		uuid = "654321";
		System.out.println("=======非内嵌属性=========="+B1.toString());
		Books bks1 = B1.getBooks();
		bks1 = new Books(3,"鲁宾孙漂流鸡",35.66,80,55);
		System.out.println("=======内嵌属性=========="+B1.toString());
		Books bks = B1.getBooks();
		bks.setBookname("格列佛游记");
		System.out.println("=======内嵌属性=========="+B1.toString());
		
		
		//发现   使用new的话并不能覆盖原先的引用类型的值，而是创建了新的对象没有存储起来，
		//对于非基本类型，非不可变类，在修改其值的时候，不能使用new来使其等于新值，而要使用修改方法，
		//例如  entity.set  stringbuilder.append
		
		//提示：new是在内存中开辟了一段新的空间
		
		
		//Q2:get会改变引用的值，但是set不会
		
		HaveBooks B2 = new HaveBooks();
		HavePapers P3 = new HavePapers();
		HavePapers P4 = new HavePapers();
		B2.setUUID("qwertyui");
		Books books1 = new Books();
		books1.setBookname("钢铁是怎样炼成的");
		B2.setBooks(books1);
		System.out.println("===========beaforeCopy================="+B2.toString());
		//父到子
		org.springframework.beans.BeanUtils.copyProperties(B2, P3);
		org.apache.commons.beanutils.BeanUtils.copyProperties(P4, B2);
		System.out.println("==========P3==========="+P3.toString());
		System.out.println("==========P4==========="+P4.toString());
		//改子查父
		
		//首先是一种无效的改法
		Books books = new Books();
		books.setBookname("骆驼祥子");
		P3.setBooks(books);
		System.out.println("==========afterCopyNouse============="+P3.toString());
		System.out.println("==========afterCopyNouse============="+B2.toString());
		
		
		//然后是有效的改法
		Books books2 = P3.getBooks();
		books2.setBookname("鲁宾孙漂流记");
		System.out.println("===========afterCopy================"+B2.toString());
		Books books3 = new Books();
		books3 = P4.getBooks();
		books3.setBookname("格列佛游记");
		System.out.println("===========afterCopy================"+B2.toString());
		
		
	}

}
