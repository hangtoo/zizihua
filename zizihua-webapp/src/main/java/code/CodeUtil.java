package code;

import codeGenerate.def.FtlDef;
import codeGenerate.factory.CodeGenerateFactory;

public class CodeUtil {

	public static void main(String[] args) {
		/** 此处修改成你的 表名 和 中文注释 ***/
		createStock();
		//createStockIndex();
		//createPe();
	}

	private static void createStock() {
		String tableName = "t_stock"; //
		String codeName = "深证成指";// 中文注释 当然你用英文也是可以的
		String entityPackage = "stock";// 实体包
		String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, true);
	}
	private static void createStockIndex() {
		String tableName = "t_stockindex"; //
		String codeName = "深证成指极点";// 中文注释 当然你用英文也是可以的
		String entityPackage = "stock";// 实体包
		String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, true);
	}
	private static void createPe() {
		String tableName = "t_pe"; //
		String codeName = "深证成指市盈率";// 中文注释 当然你用英文也是可以的
		String entityPackage = "stock";// 实体包
		String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, true);
	}

}
