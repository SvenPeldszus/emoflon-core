package org.moflon.emf.codegen.template;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenDelegationKind;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.codegen.ecore.genmodel.GenRuntimePlatform;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.moflon.emf.codegen.AbstractMoflonClassGeneratorAdapter;

public class JavaClassGenerator {
	protected static String nl;

	public static synchronized JavaClassGenerator create(final String lineSeparator) {
		nl = lineSeparator;
		final JavaClassGenerator result = new JavaClassGenerator();
		nl = null;
		return result;
	}

	public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;

	protected final String TEXT_1 = "/**";

	protected final String TEXT_2 = this.NL + " * ";

	protected final String TEXT_3 = this.NL + " */";

	protected final String TEXT_4 = this.NL + "package ";

	protected final String TEXT_5 = ";";

	protected final String TEXT_6 = this.NL + "package ";

	protected final String TEXT_7 = ";";

	protected final String TEXT_8 = this.NL;

	protected final String TEXT_9 = this.NL;

	protected final String TEXT_10 = this.NL + "/**" + this.NL + " * <!-- begin-user-doc -->" + this.NL
			+ " * A representation of the model object '<em><b>";

	protected final String TEXT_11 = "</b></em>'." + this.NL + " * <!-- end-user-doc -->";

	protected final String TEXT_12 = this.NL + " *" + this.NL + " * <!-- begin-model-doc -->" + this.NL + " * ";

	protected final String TEXT_13 = this.NL + " * <!-- end-model-doc -->";

	protected final String TEXT_14 = this.NL + " *";

	protected final String TEXT_15 = this.NL + " * <p>" + this.NL + " * The following features are supported:" + this.NL + " * <ul>";

	protected final String TEXT_16 = this.NL + " *   <li>{@link ";

	protected final String TEXT_17 = "#";

	protected final String TEXT_18 = " <em>";

	protected final String TEXT_19 = "</em>}</li>";

	protected final String TEXT_20 = this.NL + " * </ul>" + this.NL + " * </p>";

	protected final String TEXT_21 = this.NL + " *";

	protected final String TEXT_22 = this.NL + " * @see ";

	protected final String TEXT_23 = "#get";

	protected final String TEXT_24 = "()";

	protected final String TEXT_25 = this.NL + " * @model ";

	protected final String TEXT_26 = this.NL + " *        ";

	protected final String TEXT_27 = this.NL + " * @model";

	protected final String TEXT_28 = this.NL + " * @extends ";

	protected final String TEXT_29 = this.NL + " * @generated" + this.NL + " */";

	protected final String TEXT_30 = this.NL + "/**" + this.NL + " * <!-- begin-user-doc -->" + this.NL
			+ " * An implementation of the model object '<em><b>";

	protected final String TEXT_31 = "</b></em>'." + this.NL + " * <!-- end-user-doc -->" + this.NL + " * <p>";

	protected final String TEXT_32 = this.NL + " * The following features are implemented:" + this.NL + " * <ul>";

	protected final String TEXT_33 = this.NL + " *   <li>{@link ";

	protected final String TEXT_34 = "#";

	protected final String TEXT_35 = " <em>";

	protected final String TEXT_36 = "</em>}</li>";

	protected final String TEXT_37 = this.NL + " * </ul>";

	protected final String TEXT_38 = this.NL + " * </p>" + this.NL + " *" + this.NL + " * @generated" + this.NL + " */";

	protected final String TEXT_39 = this.NL + "public";

	protected final String TEXT_40 = " abstract";

	protected final String TEXT_41 = " class ";

	protected final String TEXT_42 = this.NL + "public interface ";

	protected final String TEXT_43 = this.NL + "{";

	protected final String TEXT_44 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */" + this.NL + "\t";

	protected final String TEXT_45 = " copyright = ";

	protected final String TEXT_46 = ";";

	protected final String TEXT_47 = this.NL;

	protected final String TEXT_48 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */" + this.NL + "\tpublic static final ";

	protected final String TEXT_49 = " mofDriverNumber = \"";

	protected final String TEXT_50 = "\";";

	protected final String TEXT_51 = this.NL;

	protected final String TEXT_52 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */" + this.NL
			+ "\tprivate static final long serialVersionUID = 1L;" + this.NL;

	protected final String TEXT_53 = this.NL + "\t/**" + this.NL
			+ "\t * An array of objects representing the values of non-primitive features." + this.NL
			+ "\t * <!-- begin-user-doc -->" + this.NL + "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL
			+ "\t */";

	protected final String TEXT_54 = this.NL + "\t@";

	protected final String TEXT_55 = this.NL + "\tprotected Object[] ";

	protected final String TEXT_56 = ";" + this.NL;

	protected final String TEXT_57 = this.NL + "\t/**" + this.NL
			+ "\t * A bit field representing the indices of non-primitive feature values." + this.NL
			+ "\t * <!-- begin-user-doc -->" + this.NL + "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL
			+ "\t */";

	protected final String TEXT_58 = this.NL + "\t@";

	protected final String TEXT_59 = this.NL + "\tprotected int ";

	protected final String TEXT_60 = ";" + this.NL;

	protected final String TEXT_61 = this.NL + "\t/**" + this.NL
			+ "\t * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set."
			+ this.NL + "\t * <!-- begin-user-doc -->" + this.NL + "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL
			+ "\t * @ordered" + this.NL + "\t */";

	protected final String TEXT_62 = this.NL + "\t@";

	protected final String TEXT_63 = this.NL + "\tprotected int ";

	protected final String TEXT_64 = " = 0;" + this.NL;

	protected final String TEXT_65 = this.NL + "\t/**" + this.NL + "\t * The cached setting delegate for the '{@link #";

	protected final String TEXT_66 = "() <em>";

	protected final String TEXT_67 = "</em>}' ";

	protected final String TEXT_68 = "." + this.NL + "\t * <!-- begin-user-doc -->" + this.NL + "\t * <!-- end-user-doc -->" + this.NL
			+ "\t * @see #";

	protected final String TEXT_69 = "()" + this.NL + "\t * @generated" + this.NL + "\t * @ordered" + this.NL + "\t */";

	protected final String TEXT_70 = this.NL + "\t@";

	protected final String TEXT_71 = this.NL + "\tprotected ";

	protected final String TEXT_72 = ".Internal.SettingDelegate ";

	protected final String TEXT_73 = "__ESETTING_DELEGATE = ((";

	protected final String TEXT_74 = ".Internal)";

	protected final String TEXT_75 = ").getSettingDelegate();" + this.NL;

	protected final String TEXT_76 = this.NL + "\t/**" + this.NL + "\t * The cached value of the '{@link #";

	protected final String TEXT_77 = "() <em>";

	protected final String TEXT_78 = "</em>}' ";

	protected final String TEXT_79 = "." + this.NL + "\t * <!-- begin-user-doc -->" + this.NL + "\t * <!-- end-user-doc -->" + this.NL
			+ "\t * @see #";

	protected final String TEXT_80 = "()" + this.NL + "\t * @generated" + this.NL + "\t * @ordered" + this.NL + "\t */";

	protected final String TEXT_81 = this.NL + "\t@";

	protected final String TEXT_82 = this.NL + "\tprotected ";

	protected final String TEXT_83 = " ";

	protected final String TEXT_84 = ";" + this.NL;

	protected final String TEXT_85 = this.NL + "\t/**" + this.NL + "\t * The empty value for the '{@link #";

	protected final String TEXT_86 = "() <em>";

	protected final String TEXT_87 = "</em>}' array accessor." + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @see #";

	protected final String TEXT_88 = "()" + this.NL + "\t * @generated" + this.NL + "\t * @ordered" + this.NL + "\t */";

	protected final String TEXT_89 = this.NL + "\t@SuppressWarnings(\"unchecked\")";

	protected final String TEXT_90 = this.NL + "\tprotected static final ";

	protected final String TEXT_91 = "[] ";

	protected final String TEXT_92 = "_EEMPTY_ARRAY = new ";

	protected final String TEXT_93 = " [0]";

	protected final String TEXT_94 = ";" + this.NL;

	protected final String TEXT_95 = this.NL + "\t/**" + this.NL + "\t * The default value of the '{@link #";

	protected final String TEXT_96 = "() <em>";

	protected final String TEXT_97 = "</em>}' ";

	protected final String TEXT_98 = "." + this.NL + "\t * <!-- begin-user-doc -->" + this.NL + "\t * <!-- end-user-doc -->" + this.NL
			+ "\t * @see #";

	protected final String TEXT_99 = "()" + this.NL + "\t * @generated" + this.NL + "\t * @ordered" + this.NL + "\t */";

	protected final String TEXT_100 = this.NL + "\t@SuppressWarnings(\"unchecked\")";

	protected final String TEXT_101 = this.NL + "\tprotected static final ";

	protected final String TEXT_102 = " ";

	protected final String TEXT_103 = "; // TODO The default value literal \"";

	protected final String TEXT_104 = "\" is not valid.";

	protected final String TEXT_105 = " = ";

	protected final String TEXT_106 = ";";

	protected final String TEXT_107 = this.NL;

	protected final String TEXT_108 = this.NL + "\t/**" + this.NL
			+ "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set."
			+ this.NL + "\t * <!-- begin-user-doc -->" + this.NL + "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL
			+ "\t * @ordered" + this.NL + "\t */";

	protected final String TEXT_109 = this.NL + "\t@";

	protected final String TEXT_110 = this.NL + "\tprotected int ";

	protected final String TEXT_111 = " = 0;" + this.NL;

	protected final String TEXT_112 = this.NL + "\t/**" + this.NL
			+ "\t * The offset of the flags representing the value of the '{@link #";

	protected final String TEXT_113 = "() <em>";

	protected final String TEXT_114 = "</em>}' ";

	protected final String TEXT_115 = "." + this.NL + "\t * <!-- begin-user-doc -->" + this.NL + "\t * <!-- end-user-doc -->" + this.NL
			+ "\t * @generated" + this.NL + "\t * @ordered" + this.NL + "\t */" + this.NL + "\tprotected static final int ";

	protected final String TEXT_116 = "_EFLAG_OFFSET = ";

	protected final String TEXT_117 = ";" + this.NL + "" + this.NL + "\t/**" + this.NL
			+ "\t * The flags representing the default value of the '{@link #";

	protected final String TEXT_118 = "() <em>";

	protected final String TEXT_119 = "</em>}' ";

	protected final String TEXT_120 = "." + this.NL + "\t * <!-- begin-user-doc -->" + this.NL + "\t * <!-- end-user-doc -->" + this.NL
			+ "\t * @generated" + this.NL + "\t * @ordered" + this.NL + "\t */" + this.NL + "\tprotected static final int ";

	protected final String TEXT_121 = "_EFLAG_DEFAULT = ";

	protected final String TEXT_122 = ".ordinal()";

	protected final String TEXT_123 = ".VALUES.indexOf(";

	protected final String TEXT_124 = ")";

	protected final String TEXT_125 = " << ";

	protected final String TEXT_126 = "_EFLAG_OFFSET;" + this.NL + "" + this.NL + "\t/**" + this.NL
			+ "\t * The array of enumeration values for '{@link ";

	protected final String TEXT_127 = " ";

	protected final String TEXT_128 = "}'" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL + "\t * <!-- end-user-doc -->"
			+ this.NL + "\t * @generated" + this.NL + "\t * @ordered" + this.NL + "\t */" + this.NL + "\tprivate static final ";

	protected final String TEXT_129 = "[] ";

	protected final String TEXT_130 = "_EFLAG_VALUES = ";

	protected final String TEXT_131 = ".values()";

	protected final String TEXT_132 = "(";

	protected final String TEXT_133 = "[])";

	protected final String TEXT_134 = ".VALUES.toArray(new ";

	protected final String TEXT_135 = "[";

	protected final String TEXT_136 = ".VALUES.size()])";

	protected final String TEXT_137 = ";" + this.NL;

	protected final String TEXT_138 = this.NL + "\t/**" + this.NL + "\t * The flag";

	protected final String TEXT_139 = " representing the value of the '{@link #";

	protected final String TEXT_140 = "() <em>";

	protected final String TEXT_141 = "</em>}' ";

	protected final String TEXT_142 = "." + this.NL + "\t * <!-- begin-user-doc -->" + this.NL + "\t * <!-- end-user-doc -->" + this.NL
			+ "\t * @see #";

	protected final String TEXT_143 = "()" + this.NL + "\t * @generated" + this.NL + "\t * @ordered" + this.NL + "\t */" + this.NL
			+ "\tprotected static final int ";

	protected final String TEXT_144 = "_EFLAG = ";

	protected final String TEXT_145 = " << ";

	protected final String TEXT_146 = "_EFLAG_OFFSET";

	protected final String TEXT_147 = ";" + this.NL;

	protected final String TEXT_148 = this.NL + "\t/**" + this.NL + "\t * The cached value of the '{@link #";

	protected final String TEXT_149 = "() <em>";

	protected final String TEXT_150 = "</em>}' ";

	protected final String TEXT_151 = "." + this.NL + "\t * <!-- begin-user-doc -->" + this.NL + "\t * <!-- end-user-doc -->" + this.NL
			+ "\t * @see #";

	protected final String TEXT_152 = "()" + this.NL + "\t * @generated" + this.NL + "\t * @ordered" + this.NL + "\t */";

	protected final String TEXT_153 = this.NL + "\t@";

	protected final String TEXT_154 = this.NL + "\tprotected ";

	protected final String TEXT_155 = " ";

	protected final String TEXT_156 = " = ";

	protected final String TEXT_157 = ";" + this.NL;

	protected final String TEXT_158 = this.NL + "\t/**" + this.NL
			+ "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set."
			+ this.NL + "\t * <!-- begin-user-doc -->" + this.NL + "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL
			+ "\t * @ordered" + this.NL + "\t */";

	protected final String TEXT_159 = this.NL + "\t@";

	protected final String TEXT_160 = this.NL + "\tprotected int ";

	protected final String TEXT_161 = " = 0;" + this.NL;

	protected final String TEXT_162 = this.NL + "\t/**" + this.NL + "\t * The flag representing whether the ";

	protected final String TEXT_163 = " ";

	protected final String TEXT_164 = " has been set." + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t * @ordered" + this.NL + "\t */" + this.NL
			+ "\tprotected static final int ";

	protected final String TEXT_165 = "_ESETFLAG = 1 << ";

	protected final String TEXT_166 = ";" + this.NL;

	protected final String TEXT_167 = this.NL + "\t/**" + this.NL + "\t * This is true if the ";

	protected final String TEXT_168 = " ";

	protected final String TEXT_169 = " has been set." + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t * @ordered" + this.NL + "\t */";

	protected final String TEXT_170 = this.NL + "\t@";

	protected final String TEXT_171 = this.NL + "\tprotected boolean ";

	protected final String TEXT_172 = "ESet;" + this.NL;

	protected final String TEXT_173 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */" + this.NL + "\tprivate static final int ";

	protected final String TEXT_174 = " = ";

	protected final String TEXT_175 = ".getFeatureID(";

	protected final String TEXT_176 = ") - ";

	protected final String TEXT_177 = ";" + this.NL;

	protected final String TEXT_178 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */" + this.NL + "\tprivate static final int ";

	protected final String TEXT_179 = " = ";

	protected final String TEXT_180 = ".getFeatureID(";

	protected final String TEXT_181 = ") - ";

	protected final String TEXT_182 = ";" + this.NL;

	protected final String TEXT_183 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */" + this.NL
			+ "\tprivate static final int \"EOPERATION_OFFSET_CORRECTION\" = ";

	protected final String TEXT_184 = ".getOperationID(";

	protected final String TEXT_185 = ") - ";

	protected final String TEXT_186 = ";" + this.NL;

	protected final String TEXT_187 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */" + this.NL + "\t";

	protected final String TEXT_188 = "public";

	protected final String TEXT_189 = "protected";

	protected final String TEXT_190 = " ";

	protected final String TEXT_191 = "()" + this.NL + "\t{" + this.NL + "\t\tsuper();";

	protected final String TEXT_192 = this.NL + "\t\t";

	protected final String TEXT_193 = " |= ";

	protected final String TEXT_194 = "_EFLAG";

	protected final String TEXT_195 = "_DEFAULT";

	protected final String TEXT_196 = ";";

	protected final String TEXT_197 = this.NL + "\t}" + this.NL + "" + this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_198 = this.NL + "\t@Override";

	protected final String TEXT_199 = this.NL + "\tprotected ";

	protected final String TEXT_200 = " eStaticClass()" + this.NL + "\t{" + this.NL + "\t\treturn ";

	protected final String TEXT_201 = ";" + this.NL + "\t}" + this.NL;

	protected final String TEXT_202 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_203 = this.NL + "\t@Override";

	protected final String TEXT_204 = this.NL + "\tprotected int eStaticFeatureCount()" + this.NL + "\t{" + this.NL + "\t\treturn ";

	protected final String TEXT_205 = ";" + this.NL + "\t}" + this.NL;

	protected final String TEXT_206 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_207 = this.NL + "\t";

	protected final String TEXT_208 = "[] ";

	protected final String TEXT_209 = "();" + this.NL;

	protected final String TEXT_210 = this.NL + "\tpublic ";

	protected final String TEXT_211 = "[] ";

	protected final String TEXT_212 = "()" + this.NL + "\t{";

	protected final String TEXT_213 = this.NL + "\t\t";

	protected final String TEXT_214 = " list = (";

	protected final String TEXT_215 = ")";

	protected final String TEXT_216 = "();" + this.NL + "\t\tif (list.isEmpty()) return ";

	protected final String TEXT_217 = "(";

	protected final String TEXT_218 = "[])";

	protected final String TEXT_219 = "_EEMPTY_ARRAY;";

	protected final String TEXT_220 = this.NL + "\t\tif (";

	protected final String TEXT_221 = " == null || ";

	protected final String TEXT_222 = ".isEmpty()) return ";

	protected final String TEXT_223 = "(";

	protected final String TEXT_224 = "[])";

	protected final String TEXT_225 = "_EEMPTY_ARRAY;" + this.NL + "\t\t";

	protected final String TEXT_226 = " list = (";

	protected final String TEXT_227 = ")";

	protected final String TEXT_228 = ";";

	protected final String TEXT_229 = this.NL + "\t\tlist.shrink();" + this.NL + "\t\treturn (";

	protected final String TEXT_230 = "[])list.data();" + this.NL + "\t}" + this.NL;

	protected final String TEXT_231 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_232 = this.NL + "\t";

	protected final String TEXT_233 = " get";

	protected final String TEXT_234 = "(int index);" + this.NL;

	protected final String TEXT_235 = this.NL + "\tpublic ";

	protected final String TEXT_236 = " get";

	protected final String TEXT_237 = "(int index)" + this.NL + "\t{" + this.NL + "\t\treturn ";

	protected final String TEXT_238 = "(";

	protected final String TEXT_239 = ")";

	protected final String TEXT_240 = "().get(index);" + this.NL + "\t}" + this.NL;

	protected final String TEXT_241 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_242 = this.NL + "\tint get";

	protected final String TEXT_243 = "Length();" + this.NL;

	protected final String TEXT_244 = this.NL + "\tpublic int get";

	protected final String TEXT_245 = "Length()" + this.NL + "\t{";

	protected final String TEXT_246 = this.NL + "\t\treturn ";

	protected final String TEXT_247 = "().size();";

	protected final String TEXT_248 = this.NL + "\t\treturn ";

	protected final String TEXT_249 = " == null ? 0 : ";

	protected final String TEXT_250 = ".size();";

	protected final String TEXT_251 = this.NL + "\t}" + this.NL;

	protected final String TEXT_252 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_253 = this.NL + "\tvoid set";

	protected final String TEXT_254 = "(";

	protected final String TEXT_255 = "[] new";

	protected final String TEXT_256 = ");" + this.NL;

	protected final String TEXT_257 = this.NL + "\tpublic void set";

	protected final String TEXT_258 = "(";

	protected final String TEXT_259 = "[] new";

	protected final String TEXT_260 = ")" + this.NL + "\t{" + this.NL + "\t\t((";

	protected final String TEXT_261 = ")";

	protected final String TEXT_262 = "()).setData(new";

	protected final String TEXT_263 = ".length, new";

	protected final String TEXT_264 = ");" + this.NL + "\t}" + this.NL;

	protected final String TEXT_265 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_266 = this.NL + "\tvoid set";

	protected final String TEXT_267 = "(int index, ";

	protected final String TEXT_268 = " element);" + this.NL;

	protected final String TEXT_269 = this.NL + "\tpublic void set";

	protected final String TEXT_270 = "(int index, ";

	protected final String TEXT_271 = " element)" + this.NL + "\t{" + this.NL + "\t\t";

	protected final String TEXT_272 = "().set(index, element);" + this.NL + "\t}" + this.NL;

	protected final String TEXT_273 = this.NL + "\t/**" + this.NL + "\t * Returns the value of the '<em><b>";

	protected final String TEXT_274 = "</b></em>' ";

	protected final String TEXT_275 = ".";

	protected final String TEXT_276 = this.NL + "\t * The key is of type ";

	protected final String TEXT_277 = "list of {@link ";

	protected final String TEXT_278 = "}";

	protected final String TEXT_279 = "{@link ";

	protected final String TEXT_280 = "}";

	protected final String TEXT_281 = "," + this.NL + "\t * and the value is of type ";

	protected final String TEXT_282 = "list of {@link ";

	protected final String TEXT_283 = "}";

	protected final String TEXT_284 = "{@link ";

	protected final String TEXT_285 = "}";

	protected final String TEXT_286 = ",";

	protected final String TEXT_287 = this.NL + "\t * The list contents are of type {@link ";

	protected final String TEXT_288 = "}";

	protected final String TEXT_289 = ".";

	protected final String TEXT_290 = this.NL + "\t * The default value is <code>";

	protected final String TEXT_291 = "</code>.";

	protected final String TEXT_292 = this.NL + "\t * The literals are from the enumeration {@link ";

	protected final String TEXT_293 = "}.";

	protected final String TEXT_294 = this.NL + "\t * It is bidirectional and its opposite is '{@link ";

	protected final String TEXT_295 = "#";

	protected final String TEXT_296 = " <em>";

	protected final String TEXT_297 = "</em>}'.";

	protected final String TEXT_298 = this.NL + "\t * <!-- begin-user-doc -->";

	protected final String TEXT_299 = this.NL + "\t * <p>" + this.NL + "\t * If the meaning of the '<em>";

	protected final String TEXT_300 = "</em>' ";

	protected final String TEXT_301 = " isn't clear," + this.NL + "\t * there really should be more of a description here..."
			+ this.NL + "\t * </p>";

	protected final String TEXT_302 = this.NL + "\t * <!-- end-user-doc -->";

	protected final String TEXT_303 = this.NL + "\t * <!-- begin-model-doc -->" + this.NL + "\t * ";

	protected final String TEXT_304 = this.NL + "\t * <!-- end-model-doc -->";

	protected final String TEXT_305 = this.NL + "\t * @return the value of the '<em>";

	protected final String TEXT_306 = "</em>' ";

	protected final String TEXT_307 = ".";

	protected final String TEXT_308 = this.NL + "\t * @see ";

	protected final String TEXT_309 = this.NL + "\t * @see #isSet";

	protected final String TEXT_310 = "()";

	protected final String TEXT_311 = this.NL + "\t * @see #unset";

	protected final String TEXT_312 = "()";

	protected final String TEXT_313 = this.NL + "\t * @see #set";

	protected final String TEXT_314 = "(";

	protected final String TEXT_315 = ")";

	protected final String TEXT_316 = this.NL + "\t * @see ";

	protected final String TEXT_317 = "#get";

	protected final String TEXT_318 = "()";

	protected final String TEXT_319 = this.NL + "\t * @see ";

	protected final String TEXT_320 = "#";

	protected final String TEXT_321 = this.NL + "\t * @model ";

	protected final String TEXT_322 = this.NL + "\t *        ";

	protected final String TEXT_323 = this.NL + "\t * @model";

	protected final String TEXT_324 = this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_325 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_326 = this.NL + "\t";

	protected final String TEXT_327 = " ";

	protected final String TEXT_328 = "();" + this.NL;

	protected final String TEXT_329 = this.NL + "\t@SuppressWarnings(\"unchecked\")";

	protected final String TEXT_330 = this.NL + "\tpublic ";

	protected final String TEXT_331 = " ";

	protected final String TEXT_332 = "_";

	protected final String TEXT_333 = "()" + this.NL + "\t{";

	protected final String TEXT_334 = this.NL + "\t\treturn ";

	protected final String TEXT_335 = "(";

	protected final String TEXT_336 = "(";

	protected final String TEXT_337 = ")eDynamicGet(";

	protected final String TEXT_338 = ", ";

	protected final String TEXT_339 = ", true, ";

	protected final String TEXT_340 = ")";

	protected final String TEXT_341 = ").";

	protected final String TEXT_342 = "()";

	protected final String TEXT_343 = ";";

	protected final String TEXT_344 = this.NL + "\t\treturn ";

	protected final String TEXT_345 = "(";

	protected final String TEXT_346 = "(";

	protected final String TEXT_347 = ")eGet(";

	protected final String TEXT_348 = ", true)";

	protected final String TEXT_349 = ").";

	protected final String TEXT_350 = "()";

	protected final String TEXT_351 = ";";

	protected final String TEXT_352 = this.NL + "\t\treturn ";

	protected final String TEXT_353 = "(";

	protected final String TEXT_354 = "(";

	protected final String TEXT_355 = ")";

	protected final String TEXT_356 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, true, false)";

	protected final String TEXT_357 = ").";

	protected final String TEXT_358 = "()";

	protected final String TEXT_359 = ";";

	protected final String TEXT_360 = this.NL + "\t\t";

	protected final String TEXT_361 = " ";

	protected final String TEXT_362 = " = (";

	protected final String TEXT_363 = ")eVirtualGet(";

	protected final String TEXT_364 = ");";

	protected final String TEXT_365 = this.NL + "\t\tif (";

	protected final String TEXT_366 = " == null)" + this.NL + "\t\t{";

	protected final String TEXT_367 = this.NL + "\t\t\teVirtualSet(";

	protected final String TEXT_368 = ", ";

	protected final String TEXT_369 = " = new ";

	protected final String TEXT_370 = ");";

	protected final String TEXT_371 = this.NL + "\t\t\t";

	protected final String TEXT_372 = " = new ";

	protected final String TEXT_373 = ";";

	protected final String TEXT_374 = this.NL + "\t\t}" + this.NL + "\t\treturn ";

	protected final String TEXT_375 = ";";

	protected final String TEXT_376 = this.NL + "\t\tif (eContainerFeatureID() != ";

	protected final String TEXT_377 = ") return null;" + this.NL + "\t\treturn (";

	protected final String TEXT_378 = ")eContainer();";

	protected final String TEXT_379 = this.NL + "\t\t";

	protected final String TEXT_380 = " ";

	protected final String TEXT_381 = " = (";

	protected final String TEXT_382 = ")eVirtualGet(";

	protected final String TEXT_383 = ", ";

	protected final String TEXT_384 = ");";

	protected final String TEXT_385 = this.NL + "\t\tif (";

	protected final String TEXT_386 = " != null && ";

	protected final String TEXT_387 = ".eIsProxy())" + this.NL + "\t\t{" + this.NL + "\t\t\t";

	protected final String TEXT_388 = " old";

	protected final String TEXT_389 = " = (";

	protected final String TEXT_390 = ")";

	protected final String TEXT_391 = ";" + this.NL + "\t\t\t";

	protected final String TEXT_392 = " = ";

	protected final String TEXT_393 = "eResolveProxy(old";

	protected final String TEXT_394 = ");" + this.NL + "\t\t\tif (";

	protected final String TEXT_395 = " != old";

	protected final String TEXT_396 = ")" + this.NL + "\t\t\t{";

	protected final String TEXT_397 = this.NL + "\t\t\t\t";

	protected final String TEXT_398 = " new";

	protected final String TEXT_399 = " = (";

	protected final String TEXT_400 = ")";

	protected final String TEXT_401 = ";";

	protected final String TEXT_402 = this.NL + "\t\t\t\t";

	protected final String TEXT_403 = " msgs = old";

	protected final String TEXT_404 = ".eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";

	protected final String TEXT_405 = ", null, null);";

	protected final String TEXT_406 = this.NL + "\t\t\t\t";

	protected final String TEXT_407 = " msgs =  old";

	protected final String TEXT_408 = ".eInverseRemove(this, ";

	protected final String TEXT_409 = ", ";

	protected final String TEXT_410 = ".class, null);";

	protected final String TEXT_411 = this.NL + "\t\t\t\tif (new";

	protected final String TEXT_412 = ".eInternalContainer() == null)" + this.NL + "\t\t\t\t{";

	protected final String TEXT_413 = this.NL + "\t\t\t\t\tmsgs = new";

	protected final String TEXT_414 = ".eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";

	protected final String TEXT_415 = ", null, msgs);";

	protected final String TEXT_416 = this.NL + "\t\t\t\t\tmsgs =  new";

	protected final String TEXT_417 = ".eInverseAdd(this, ";

	protected final String TEXT_418 = ", ";

	protected final String TEXT_419 = ".class, msgs);";

	protected final String TEXT_420 = this.NL + "\t\t\t\t}" + this.NL + "\t\t\t\tif (msgs != null) msgs.dispatch();";

	protected final String TEXT_421 = this.NL + "\t\t\t\teVirtualSet(";

	protected final String TEXT_422 = ", ";

	protected final String TEXT_423 = ");";

	protected final String TEXT_424 = this.NL + "\t\t\t\tif (eNotificationRequired())" + this.NL + "\t\t\t\t\teNotify(new ";

	protected final String TEXT_425 = "(this, ";

	protected final String TEXT_426 = ".RESOLVE, ";

	protected final String TEXT_427 = ", old";

	protected final String TEXT_428 = ", ";

	protected final String TEXT_429 = "));";

	protected final String TEXT_430 = this.NL + "\t\t\t}" + this.NL + "\t\t}";

	protected final String TEXT_431 = this.NL + "\t\treturn (";

	protected final String TEXT_432 = ")eVirtualGet(";

	protected final String TEXT_433 = ", ";

	protected final String TEXT_434 = ");";

	protected final String TEXT_435 = this.NL + "\t\treturn (";

	protected final String TEXT_436 = " & ";

	protected final String TEXT_437 = "_EFLAG) != 0;";

	protected final String TEXT_438 = this.NL + "\t\treturn ";

	protected final String TEXT_439 = "_EFLAG_VALUES[(";

	protected final String TEXT_440 = " & ";

	protected final String TEXT_441 = "_EFLAG) >>> ";

	protected final String TEXT_442 = "_EFLAG_OFFSET];";

	protected final String TEXT_443 = this.NL + "\t\treturn ";

	protected final String TEXT_444 = ";";

	protected final String TEXT_445 = this.NL + "\t\t";

	protected final String TEXT_446 = " ";

	protected final String TEXT_447 = " = basicGet";

	protected final String TEXT_448 = "();" + this.NL + "\t\treturn ";

	protected final String TEXT_449 = " != null && ";

	protected final String TEXT_450 = ".eIsProxy() ? ";

	protected final String TEXT_451 = "eResolveProxy((";

	protected final String TEXT_452 = ")";

	protected final String TEXT_453 = ") : ";

	protected final String TEXT_454 = ";";

	protected final String TEXT_455 = this.NL + "\t\treturn new ";

	protected final String TEXT_456 = "((";

	protected final String TEXT_457 = ".Internal)((";

	protected final String TEXT_458 = ".Internal.Wrapper)get";

	protected final String TEXT_459 = "()).featureMap().";

	protected final String TEXT_460 = "list(";

	protected final String TEXT_461 = "));";

	protected final String TEXT_462 = this.NL + "\t\treturn (";

	protected final String TEXT_463 = ")get";

	protected final String TEXT_464 = "().";

	protected final String TEXT_465 = "list(";

	protected final String TEXT_466 = ");";

	protected final String TEXT_467 = this.NL + "\t\treturn ((";

	protected final String TEXT_468 = ".Internal.Wrapper)get";

	protected final String TEXT_469 = "()).featureMap().list(";

	protected final String TEXT_470 = ");";

	protected final String TEXT_471 = this.NL + "\t\treturn get";

	protected final String TEXT_472 = "().list(";

	protected final String TEXT_473 = ");";

	protected final String TEXT_474 = this.NL + "\t\treturn ";

	protected final String TEXT_475 = "(";

	protected final String TEXT_476 = "(";

	protected final String TEXT_477 = ")";

	protected final String TEXT_478 = "((";

	protected final String TEXT_479 = ".Internal.Wrapper)get";

	protected final String TEXT_480 = "()).featureMap().get(";

	protected final String TEXT_481 = ", true)";

	protected final String TEXT_482 = ").";

	protected final String TEXT_483 = "()";

	protected final String TEXT_484 = ";";

	protected final String TEXT_485 = this.NL + "\t\treturn ";

	protected final String TEXT_486 = "(";

	protected final String TEXT_487 = "(";

	protected final String TEXT_488 = ")";

	protected final String TEXT_489 = "get";

	protected final String TEXT_490 = "().get(";

	protected final String TEXT_491 = ", true)";

	protected final String TEXT_492 = ").";

	protected final String TEXT_493 = "()";

	protected final String TEXT_494 = ";";

	protected final String TEXT_495 = this.NL + "\t\t";

	protected final String TEXT_496 = this.NL + "\t\t";

	protected final String TEXT_497 = this.NL + "\t\t// TODO: implement this method to return the '";

	protected final String TEXT_498 = "' ";

	protected final String TEXT_499 = this.NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT";

	protected final String TEXT_500 = this.NL
			+ "\t\t// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting"
			+ this.NL + "\t\t// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.";

	protected final String TEXT_501 = "EcoreEMap";

	protected final String TEXT_502 = "BasicFeatureMap";

	protected final String TEXT_503 = "EcoreEList";

	protected final String TEXT_504 = " should be used.";

	protected final String TEXT_505 = this.NL + "\t\tthrow new UnsupportedOperationException();";

	protected final String TEXT_506 = this.NL + "\t}" + this.NL;

	protected final String TEXT_507 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_508 = this.NL + "\tpublic ";

	protected final String TEXT_509 = " basicGet";

	protected final String TEXT_510 = "()" + this.NL + "\t{";

	protected final String TEXT_511 = this.NL + "\t\treturn (";

	protected final String TEXT_512 = ")eDynamicGet(";

	protected final String TEXT_513 = ", ";

	protected final String TEXT_514 = ", false, ";

	protected final String TEXT_515 = ");";

	protected final String TEXT_516 = this.NL + "\t\treturn ";

	protected final String TEXT_517 = "(";

	protected final String TEXT_518 = "(";

	protected final String TEXT_519 = ")";

	protected final String TEXT_520 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, false, false)";

	protected final String TEXT_521 = ").";

	protected final String TEXT_522 = "()";

	protected final String TEXT_523 = ";";

	protected final String TEXT_524 = this.NL + "\t\tif (eContainerFeatureID() != ";

	protected final String TEXT_525 = ") return null;" + this.NL + "\t\treturn (";

	protected final String TEXT_526 = ")eInternalContainer();";

	protected final String TEXT_527 = this.NL + "\t\treturn (";

	protected final String TEXT_528 = ")eVirtualGet(";

	protected final String TEXT_529 = ");";

	protected final String TEXT_530 = this.NL + "\t\treturn ";

	protected final String TEXT_531 = ";";

	protected final String TEXT_532 = this.NL + "\t\treturn (";

	protected final String TEXT_533 = ")((";

	protected final String TEXT_534 = ".Internal.Wrapper)get";

	protected final String TEXT_535 = "()).featureMap().get(";

	protected final String TEXT_536 = ", false);";

	protected final String TEXT_537 = this.NL + "\t\treturn (";

	protected final String TEXT_538 = ")get";

	protected final String TEXT_539 = "().get(";

	protected final String TEXT_540 = ", false);";

	protected final String TEXT_541 = this.NL + "\t\t// TODO: implement this method to return the '";

	protected final String TEXT_542 = "' ";

	protected final String TEXT_543 = this.NL + "\t\t// -> do not perform proxy resolution" + this.NL
			+ "\t\t// Ensure that you remove @generated or mark it @generated NOT" + this.NL
			+ "\t\tthrow new UnsupportedOperationException();";

	protected final String TEXT_544 = this.NL + "\t}" + this.NL;

	protected final String TEXT_545 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_546 = this.NL + "\tpublic ";

	protected final String TEXT_547 = " basicSet";

	protected final String TEXT_548 = "(";

	protected final String TEXT_549 = " new";

	protected final String TEXT_550 = ", ";

	protected final String TEXT_551 = " msgs)" + this.NL + "\t{";

	protected final String TEXT_552 = this.NL + "\t\tmsgs = eBasicSetContainer((";

	protected final String TEXT_553 = ")new";

	protected final String TEXT_554 = ", ";

	protected final String TEXT_555 = ", msgs);";

	protected final String TEXT_556 = this.NL + "\t\treturn msgs;";

	protected final String TEXT_557 = this.NL + "\t\tmsgs = eDynamicInverseAdd((";

	protected final String TEXT_558 = ")new";

	protected final String TEXT_559 = ", ";

	protected final String TEXT_560 = ", msgs);";

	protected final String TEXT_561 = this.NL + "\t\treturn msgs;";

	protected final String TEXT_562 = this.NL + "\t\tObject old";

	protected final String TEXT_563 = " = eVirtualSet(";

	protected final String TEXT_564 = ", new";

	protected final String TEXT_565 = ");";

	protected final String TEXT_566 = this.NL + "\t\t";

	protected final String TEXT_567 = " old";

	protected final String TEXT_568 = " = ";

	protected final String TEXT_569 = ";" + this.NL + "\t\t";

	protected final String TEXT_570 = " = new";

	protected final String TEXT_571 = ";";

	protected final String TEXT_572 = this.NL + "\t\tboolean isSetChange = old";

	protected final String TEXT_573 = " == EVIRTUAL_NO_VALUE;";

	protected final String TEXT_574 = this.NL + "\t\tboolean old";

	protected final String TEXT_575 = "ESet = (";

	protected final String TEXT_576 = " & ";

	protected final String TEXT_577 = "_ESETFLAG) != 0;";

	protected final String TEXT_578 = this.NL + "\t\t";

	protected final String TEXT_579 = " |= ";

	protected final String TEXT_580 = "_ESETFLAG;";

	protected final String TEXT_581 = this.NL + "\t\tboolean old";

	protected final String TEXT_582 = "ESet = ";

	protected final String TEXT_583 = "ESet;";

	protected final String TEXT_584 = this.NL + "\t\t";

	protected final String TEXT_585 = "ESet = true;";

	protected final String TEXT_586 = this.NL + "\t\tif (eNotificationRequired())" + this.NL + "\t\t{";

	protected final String TEXT_587 = this.NL + "\t\t\t";

	protected final String TEXT_588 = " notification = new ";

	protected final String TEXT_589 = "(this, ";

	protected final String TEXT_590 = ".SET, ";

	protected final String TEXT_591 = ", ";

	protected final String TEXT_592 = "isSetChange ? null : old";

	protected final String TEXT_593 = "old";

	protected final String TEXT_594 = ", new";

	protected final String TEXT_595 = ", ";

	protected final String TEXT_596 = "isSetChange";

	protected final String TEXT_597 = "!old";

	protected final String TEXT_598 = "ESet";

	protected final String TEXT_599 = ");";

	protected final String TEXT_600 = this.NL + "\t\t\t";

	protected final String TEXT_601 = " notification = new ";

	protected final String TEXT_602 = "(this, ";

	protected final String TEXT_603 = ".SET, ";

	protected final String TEXT_604 = ", ";

	protected final String TEXT_605 = "old";

	protected final String TEXT_606 = " == EVIRTUAL_NO_VALUE ? null : old";

	protected final String TEXT_607 = "old";

	protected final String TEXT_608 = ", new";

	protected final String TEXT_609 = ");";

	protected final String TEXT_610 = this.NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);"
			+ this.NL + "\t\t}";

	protected final String TEXT_611 = this.NL + "\t\treturn msgs;";

	protected final String TEXT_612 = this.NL + "\t\treturn ((";

	protected final String TEXT_613 = ".Internal)((";

	protected final String TEXT_614 = ".Internal.Wrapper)get";

	protected final String TEXT_615 = "()).featureMap()).basicAdd(";

	protected final String TEXT_616 = ", new";

	protected final String TEXT_617 = ", msgs);";

	protected final String TEXT_618 = this.NL + "\t\treturn ((";

	protected final String TEXT_619 = ".Internal)get";

	protected final String TEXT_620 = "()).basicAdd(";

	protected final String TEXT_621 = ", new";

	protected final String TEXT_622 = ", msgs);";

	protected final String TEXT_623 = this.NL + "\t\t// TODO: implement this method to set the contained '";

	protected final String TEXT_624 = "' ";

	protected final String TEXT_625 = this.NL
			+ "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + this.NL
			+ "\t\t// -> do not modify other features" + this.NL
			+ "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)"
			+ this.NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + this.NL
			+ "\t\tthrow new UnsupportedOperationException();";

	protected final String TEXT_626 = this.NL + "\t}" + this.NL;

	protected final String TEXT_627 = this.NL + "\t/**" + this.NL + "\t * Sets the value of the '{@link ";

	protected final String TEXT_628 = "#";

	protected final String TEXT_629 = " <em>";

	protected final String TEXT_630 = "</em>}' ";

	protected final String TEXT_631 = ".";

	protected final String TEXT_632 = this.NL + "\t * <!-- begin-user-doc -->" + this.NL + "\t * <!-- end-user-doc -->" + this.NL
			+ "\t * @param value the new value of the '<em>";

	protected final String TEXT_633 = "</em>' ";

	protected final String TEXT_634 = ".";

	protected final String TEXT_635 = this.NL + "\t * @see ";

	protected final String TEXT_636 = this.NL + "\t * @see #isSet";

	protected final String TEXT_637 = "()";

	protected final String TEXT_638 = this.NL + "\t * @see #unset";

	protected final String TEXT_639 = "()";

	protected final String TEXT_640 = this.NL + "\t * @see #";

	protected final String TEXT_641 = "()" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_642 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_643 = this.NL + "\tvoid set";

	protected final String TEXT_644 = "(";

	protected final String TEXT_645 = " value);" + this.NL;

	protected final String TEXT_646 = this.NL + "\tpublic void set";

	protected final String TEXT_647 = "_";

	protected final String TEXT_648 = "(";

	protected final String TEXT_649 = " ";

	protected final String TEXT_650 = ")" + this.NL + "\t{";

	protected final String TEXT_651 = this.NL + "\t\teDynamicSet(";

	protected final String TEXT_652 = ", ";

	protected final String TEXT_653 = ", ";

	protected final String TEXT_654 = "new ";

	protected final String TEXT_655 = "(";

	protected final String TEXT_656 = "new";

	protected final String TEXT_657 = ")";

	protected final String TEXT_658 = ");";

	protected final String TEXT_659 = this.NL + "\t\teSet(";

	protected final String TEXT_660 = ", ";

	protected final String TEXT_661 = "new ";

	protected final String TEXT_662 = "(";

	protected final String TEXT_663 = "new";

	protected final String TEXT_664 = ")";

	protected final String TEXT_665 = ");";

	protected final String TEXT_666 = this.NL + "\t\t";

	protected final String TEXT_667 = "__ESETTING_DELEGATE.dynamicSet(this, null, 0, ";

	protected final String TEXT_668 = "new ";

	protected final String TEXT_669 = "(";

	protected final String TEXT_670 = "new";

	protected final String TEXT_671 = ")";

	protected final String TEXT_672 = ");";

	protected final String TEXT_673 = this.NL + "\t\tif (new";

	protected final String TEXT_674 = " != eInternalContainer() || (eContainerFeatureID() != ";

	protected final String TEXT_675 = " && new";

	protected final String TEXT_676 = " != null))" + this.NL + "\t\t{" + this.NL + "\t\t\tif (";

	protected final String TEXT_677 = ".isAncestor(this, ";

	protected final String TEXT_678 = "new";

	protected final String TEXT_679 = "))" + this.NL + "\t\t\t\tthrow new ";

	protected final String TEXT_680 = "(\"Recursive containment not allowed for \" + toString());";

	protected final String TEXT_681 = this.NL + "\t\t\t";

	protected final String TEXT_682 = " msgs = null;" + this.NL + "\t\t\tif (eInternalContainer() != null)" + this.NL
			+ "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + this.NL + "\t\t\tif (new";

	protected final String TEXT_683 = " != null)" + this.NL + "\t\t\t\tmsgs = ((";

	protected final String TEXT_684 = ")new";

	protected final String TEXT_685 = ").eInverseAdd(this, ";

	protected final String TEXT_686 = ", ";

	protected final String TEXT_687 = ".class, msgs);" + this.NL + "\t\t\tmsgs = basicSet";

	protected final String TEXT_688 = "(";

	protected final String TEXT_689 = "new";

	protected final String TEXT_690 = ", msgs);" + this.NL + "\t\t\tif (msgs != null) msgs.dispatch();" + this.NL + "\t\t}";

	protected final String TEXT_691 = this.NL + "\t\telse if (eNotificationRequired())" + this.NL + "\t\t\teNotify(new ";

	protected final String TEXT_692 = "(this, ";

	protected final String TEXT_693 = ".SET, ";

	protected final String TEXT_694 = ", new";

	protected final String TEXT_695 = ", new";

	protected final String TEXT_696 = "));";

	protected final String TEXT_697 = this.NL + "\t\t";

	protected final String TEXT_698 = " ";

	protected final String TEXT_699 = " = (";

	protected final String TEXT_700 = ")eVirtualGet(";

	protected final String TEXT_701 = ");";

	protected final String TEXT_702 = this.NL + "\t\tif (new";

	protected final String TEXT_703 = " != ";

	protected final String TEXT_704 = ")" + this.NL + "\t\t{" + this.NL + "\t\t\t";

	protected final String TEXT_705 = " msgs = null;" + this.NL + "\t\t\tif (";

	protected final String TEXT_706 = " != null)";

	protected final String TEXT_707 = this.NL + "\t\t\t\tmsgs = ((";

	protected final String TEXT_708 = ")";

	protected final String TEXT_709 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";

	protected final String TEXT_710 = ", null, msgs);" + this.NL + "\t\t\tif (new";

	protected final String TEXT_711 = " != null)" + this.NL + "\t\t\t\tmsgs = ((";

	protected final String TEXT_712 = ")new";

	protected final String TEXT_713 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";

	protected final String TEXT_714 = ", null, msgs);";

	protected final String TEXT_715 = this.NL + "\t\t\t\tmsgs = ((";

	protected final String TEXT_716 = ")";

	protected final String TEXT_717 = ").eInverseRemove(this, ";

	protected final String TEXT_718 = ", ";

	protected final String TEXT_719 = ".class, msgs);" + this.NL + "\t\t\tif (new";

	protected final String TEXT_720 = " != null)" + this.NL + "\t\t\t\tmsgs = ((";

	protected final String TEXT_721 = ")new";

	protected final String TEXT_722 = ").eInverseAdd(this, ";

	protected final String TEXT_723 = ", ";

	protected final String TEXT_724 = ".class, msgs);";

	protected final String TEXT_725 = this.NL + "\t\t\tmsgs = basicSet";

	protected final String TEXT_726 = "(";

	protected final String TEXT_727 = "new";

	protected final String TEXT_728 = ", msgs);" + this.NL + "\t\t\tif (msgs != null) msgs.dispatch();" + this.NL + "\t\t}";

	protected final String TEXT_729 = this.NL + "\t\telse" + this.NL + "\t\t{";

	protected final String TEXT_730 = this.NL + "\t\t\tboolean old";

	protected final String TEXT_731 = "ESet = eVirtualIsSet(";

	protected final String TEXT_732 = ");";

	protected final String TEXT_733 = this.NL + "\t\t\tboolean old";

	protected final String TEXT_734 = "ESet = (";

	protected final String TEXT_735 = " & ";

	protected final String TEXT_736 = "_ESETFLAG) != 0;";

	protected final String TEXT_737 = this.NL + "\t\t\t";

	protected final String TEXT_738 = " |= ";

	protected final String TEXT_739 = "_ESETFLAG;";

	protected final String TEXT_740 = this.NL + "\t\t\tboolean old";

	protected final String TEXT_741 = "ESet = ";

	protected final String TEXT_742 = "ESet;";

	protected final String TEXT_743 = this.NL + "\t\t\t";

	protected final String TEXT_744 = "ESet = true;";

	protected final String TEXT_745 = this.NL + "\t\t\tif (eNotificationRequired())" + this.NL + "\t\t\t\teNotify(new ";

	protected final String TEXT_746 = "(this, ";

	protected final String TEXT_747 = ".SET, ";

	protected final String TEXT_748 = ", new";

	protected final String TEXT_749 = ", new";

	protected final String TEXT_750 = ", !old";

	protected final String TEXT_751 = "ESet));";

	protected final String TEXT_752 = this.NL + "\t\t}";

	protected final String TEXT_753 = this.NL + "\t\telse if (eNotificationRequired())" + this.NL + "\t\t\teNotify(new ";

	protected final String TEXT_754 = "(this, ";

	protected final String TEXT_755 = ".SET, ";

	protected final String TEXT_756 = ", new";

	protected final String TEXT_757 = ", new";

	protected final String TEXT_758 = "));";

	protected final String TEXT_759 = this.NL + "\t\t";

	protected final String TEXT_760 = " old";

	protected final String TEXT_761 = " = (";

	protected final String TEXT_762 = " & ";

	protected final String TEXT_763 = "_EFLAG) != 0;";

	protected final String TEXT_764 = this.NL + "\t\t";

	protected final String TEXT_765 = " old";

	protected final String TEXT_766 = " = ";

	protected final String TEXT_767 = "_EFLAG_VALUES[(";

	protected final String TEXT_768 = " & ";

	protected final String TEXT_769 = "_EFLAG) >>> ";

	protected final String TEXT_770 = "_EFLAG_OFFSET];";

	protected final String TEXT_771 = this.NL + "\t\tif (new";

	protected final String TEXT_772 = ") ";

	protected final String TEXT_773 = " |= ";

	protected final String TEXT_774 = "_EFLAG; else ";

	protected final String TEXT_775 = " &= ~";

	protected final String TEXT_776 = "_EFLAG;";

	protected final String TEXT_777 = this.NL + "\t\tif (new";

	protected final String TEXT_778 = " == null) new";

	protected final String TEXT_779 = " = ";

	protected final String TEXT_780 = "_EDEFAULT;" + this.NL + "\t\t";

	protected final String TEXT_781 = " = ";

	protected final String TEXT_782 = " & ~";

	protected final String TEXT_783 = "_EFLAG | ";

	protected final String TEXT_784 = "new";

	protected final String TEXT_785 = ".ordinal()";

	protected final String TEXT_786 = ".VALUES.indexOf(new";

	protected final String TEXT_787 = ")";

	protected final String TEXT_788 = " << ";

	protected final String TEXT_789 = "_EFLAG_OFFSET;";

	protected final String TEXT_790 = this.NL + "\t\t";

	protected final String TEXT_791 = " old";

	protected final String TEXT_792 = " = ";

	protected final String TEXT_793 = ";";

	protected final String TEXT_794 = this.NL + "\t\t";

	protected final String TEXT_795 = " ";

	protected final String TEXT_796 = " = new";

	protected final String TEXT_797 = " == null ? ";

	protected final String TEXT_798 = " : new";

	protected final String TEXT_799 = ";";

	protected final String TEXT_800 = this.NL + "\t\t";

	protected final String TEXT_801 = " = new";

	protected final String TEXT_802 = " == null ? ";

	protected final String TEXT_803 = " : new";

	protected final String TEXT_804 = ";";

	protected final String TEXT_805 = this.NL + "\t\t";

	protected final String TEXT_806 = " ";

	protected final String TEXT_807 = " = ";

	protected final String TEXT_808 = "new";

	protected final String TEXT_809 = ";";

	protected final String TEXT_810 = this.NL + "\t\t";

	protected final String TEXT_811 = " = ";

	protected final String TEXT_812 = "new";

	protected final String TEXT_813 = ";";

	protected final String TEXT_814 = this.NL + "\t\tObject old";

	protected final String TEXT_815 = " = eVirtualSet(";

	protected final String TEXT_816 = ", ";

	protected final String TEXT_817 = ");";

	protected final String TEXT_818 = this.NL + "\t\tboolean isSetChange = old";

	protected final String TEXT_819 = " == EVIRTUAL_NO_VALUE;";

	protected final String TEXT_820 = this.NL + "\t\tboolean old";

	protected final String TEXT_821 = "ESet = (";

	protected final String TEXT_822 = " & ";

	protected final String TEXT_823 = "_ESETFLAG) != 0;";

	protected final String TEXT_824 = this.NL + "\t\t";

	protected final String TEXT_825 = " |= ";

	protected final String TEXT_826 = "_ESETFLAG;";

	protected final String TEXT_827 = this.NL + "\t\tboolean old";

	protected final String TEXT_828 = "ESet = ";

	protected final String TEXT_829 = "ESet;";

	protected final String TEXT_830 = this.NL + "\t\t";

	protected final String TEXT_831 = "ESet = true;";

	protected final String TEXT_832 = this.NL + "\t\tif (eNotificationRequired())" + this.NL + "\t\t\teNotify(new ";

	protected final String TEXT_833 = "(this, ";

	protected final String TEXT_834 = ".SET, ";

	protected final String TEXT_835 = ", ";

	protected final String TEXT_836 = "isSetChange ? ";

	protected final String TEXT_837 = " : old";

	protected final String TEXT_838 = "old";

	protected final String TEXT_839 = ", ";

	protected final String TEXT_840 = "new";

	protected final String TEXT_841 = ", ";

	protected final String TEXT_842 = "isSetChange";

	protected final String TEXT_843 = "!old";

	protected final String TEXT_844 = "ESet";

	protected final String TEXT_845 = "));";

	protected final String TEXT_846 = this.NL + "\t\tif (eNotificationRequired())" + this.NL + "\t\t\teNotify(new ";

	protected final String TEXT_847 = "(this, ";

	protected final String TEXT_848 = ".SET, ";

	protected final String TEXT_849 = ", ";

	protected final String TEXT_850 = "old";

	protected final String TEXT_851 = " == EVIRTUAL_NO_VALUE ? ";

	protected final String TEXT_852 = " : old";

	protected final String TEXT_853 = "old";

	protected final String TEXT_854 = ", ";

	protected final String TEXT_855 = "new";

	protected final String TEXT_856 = "));";

	protected final String TEXT_857 = this.NL + "\t\t((";

	protected final String TEXT_858 = ".Internal)((";

	protected final String TEXT_859 = ".Internal.Wrapper)get";

	protected final String TEXT_860 = "()).featureMap()).set(";

	protected final String TEXT_861 = ", ";

	protected final String TEXT_862 = "new ";

	protected final String TEXT_863 = "(";

	protected final String TEXT_864 = "new";

	protected final String TEXT_865 = ")";

	protected final String TEXT_866 = ");";

	protected final String TEXT_867 = this.NL + "\t\t((";

	protected final String TEXT_868 = ".Internal)get";

	protected final String TEXT_869 = "()).set(";

	protected final String TEXT_870 = ", ";

	protected final String TEXT_871 = "new ";

	protected final String TEXT_872 = "(";

	protected final String TEXT_873 = "new";

	protected final String TEXT_874 = ")";

	protected final String TEXT_875 = ");";

	protected final String TEXT_876 = this.NL + "\t\t";

	protected final String TEXT_877 = this.NL + "\t\t// TODO: implement this method to set the '";

	protected final String TEXT_878 = "' ";

	protected final String TEXT_879 = this.NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + this.NL
			+ "\t\tthrow new UnsupportedOperationException();";

	protected final String TEXT_880 = this.NL + "\t}" + this.NL;

	protected final String TEXT_881 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_882 = this.NL + "\tpublic ";

	protected final String TEXT_883 = " basicUnset";

	protected final String TEXT_884 = "(";

	protected final String TEXT_885 = " msgs)" + this.NL + "\t{";

	protected final String TEXT_886 = this.NL + "\t\treturn eDynamicInverseRemove((";

	protected final String TEXT_887 = ")";

	protected final String TEXT_888 = "basicGet";

	protected final String TEXT_889 = "(), ";

	protected final String TEXT_890 = ", msgs);";

	protected final String TEXT_891 = "Object old";

	protected final String TEXT_892 = " = ";

	protected final String TEXT_893 = "eVirtualUnset(";

	protected final String TEXT_894 = ");";

	protected final String TEXT_895 = this.NL + "\t\t";

	protected final String TEXT_896 = " old";

	protected final String TEXT_897 = " = ";

	protected final String TEXT_898 = ";";

	protected final String TEXT_899 = this.NL + "\t\t";

	protected final String TEXT_900 = " = null;";

	protected final String TEXT_901 = this.NL + "\t\tboolean isSetChange = old";

	protected final String TEXT_902 = " != EVIRTUAL_NO_VALUE;";

	protected final String TEXT_903 = this.NL + "\t\tboolean old";

	protected final String TEXT_904 = "ESet = (";

	protected final String TEXT_905 = " & ";

	protected final String TEXT_906 = "_ESETFLAG) != 0;";

	protected final String TEXT_907 = this.NL + "\t\t";

	protected final String TEXT_908 = " &= ~";

	protected final String TEXT_909 = "_ESETFLAG;";

	protected final String TEXT_910 = this.NL + "\t\tboolean old";

	protected final String TEXT_911 = "ESet = ";

	protected final String TEXT_912 = "ESet;";

	protected final String TEXT_913 = this.NL + "\t\t";

	protected final String TEXT_914 = "ESet = false;";

	protected final String TEXT_915 = this.NL + "\t\tif (eNotificationRequired())" + this.NL + "\t\t{" + this.NL + "\t\t\t";

	protected final String TEXT_916 = " notification = new ";

	protected final String TEXT_917 = "(this, ";

	protected final String TEXT_918 = ".UNSET, ";

	protected final String TEXT_919 = ", ";

	protected final String TEXT_920 = "isSetChange ? old";

	protected final String TEXT_921 = " : null";

	protected final String TEXT_922 = "old";

	protected final String TEXT_923 = ", null, ";

	protected final String TEXT_924 = "isSetChange";

	protected final String TEXT_925 = "old";

	protected final String TEXT_926 = "ESet";

	protected final String TEXT_927 = ");" + this.NL
			+ "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + this.NL + "\t\t}" + this.NL
			+ "\t\treturn msgs;";

	protected final String TEXT_928 = this.NL + "\t\t// TODO: implement this method to unset the contained '";

	protected final String TEXT_929 = "' ";

	protected final String TEXT_930 = this.NL
			+ "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + this.NL
			+ "\t\t// -> do not modify other features" + this.NL
			+ "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)"
			+ this.NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + this.NL
			+ "\t\tthrow new UnsupportedOperationException();";

	protected final String TEXT_931 = this.NL + "\t}" + this.NL;

	protected final String TEXT_932 = this.NL + "\t/**" + this.NL + "\t * Unsets the value of the '{@link ";

	protected final String TEXT_933 = "#";

	protected final String TEXT_934 = " <em>";

	protected final String TEXT_935 = "</em>}' ";

	protected final String TEXT_936 = ".";

	protected final String TEXT_937 = this.NL + "\t * <!-- begin-user-doc -->" + this.NL + "\t * <!-- end-user-doc -->";

	protected final String TEXT_938 = this.NL + "\t * @see #isSet";

	protected final String TEXT_939 = "()";

	protected final String TEXT_940 = this.NL + "\t * @see #";

	protected final String TEXT_941 = "()";

	protected final String TEXT_942 = this.NL + "\t * @see #set";

	protected final String TEXT_943 = "(";

	protected final String TEXT_944 = ")";

	protected final String TEXT_945 = this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_946 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_947 = this.NL + "\tvoid unset";

	protected final String TEXT_948 = "();" + this.NL;

	protected final String TEXT_949 = this.NL + "\tpublic void unset";

	protected final String TEXT_950 = "_";

	protected final String TEXT_951 = "()" + this.NL + "\t{";

	protected final String TEXT_952 = this.NL + "\t\teDynamicUnset(";

	protected final String TEXT_953 = ", ";

	protected final String TEXT_954 = ");";

	protected final String TEXT_955 = this.NL + "\t\teUnset(";

	protected final String TEXT_956 = ");";

	protected final String TEXT_957 = this.NL + "\t\t";

	protected final String TEXT_958 = "__ESETTING_DELEGATE.dynamicUnset(this, null, 0);";

	protected final String TEXT_959 = this.NL + "\t\t";

	protected final String TEXT_960 = " ";

	protected final String TEXT_961 = " = (";

	protected final String TEXT_962 = ")eVirtualGet(";

	protected final String TEXT_963 = ");";

	protected final String TEXT_964 = this.NL + "\t\tif (";

	protected final String TEXT_965 = " != null) ((";

	protected final String TEXT_966 = ".Unsettable";

	protected final String TEXT_967 = ")";

	protected final String TEXT_968 = ").unset();";

	protected final String TEXT_969 = this.NL + "\t\t";

	protected final String TEXT_970 = " ";

	protected final String TEXT_971 = " = (";

	protected final String TEXT_972 = ")eVirtualGet(";

	protected final String TEXT_973 = ");";

	protected final String TEXT_974 = this.NL + "\t\tif (";

	protected final String TEXT_975 = " != null)" + this.NL + "\t\t{" + this.NL + "\t\t\t";

	protected final String TEXT_976 = " msgs = null;";

	protected final String TEXT_977 = this.NL + "\t\t\tmsgs = ((";

	protected final String TEXT_978 = ")";

	protected final String TEXT_979 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";

	protected final String TEXT_980 = ", null, msgs);";

	protected final String TEXT_981 = this.NL + "\t\t\tmsgs = ((";

	protected final String TEXT_982 = ")";

	protected final String TEXT_983 = ").eInverseRemove(this, ";

	protected final String TEXT_984 = ", ";

	protected final String TEXT_985 = ".class, msgs);";

	protected final String TEXT_986 = this.NL + "\t\t\tmsgs = basicUnset";

	protected final String TEXT_987 = "(msgs);" + this.NL + "\t\t\tif (msgs != null) msgs.dispatch();" + this.NL + "\t\t}" + this.NL
			+ "\t\telse" + this.NL + "\t\t{";

	protected final String TEXT_988 = this.NL + "\t\t\tboolean old";

	protected final String TEXT_989 = "ESet = eVirtualIsSet(";

	protected final String TEXT_990 = ");";

	protected final String TEXT_991 = this.NL + "\t\t\tboolean old";

	protected final String TEXT_992 = "ESet = (";

	protected final String TEXT_993 = " & ";

	protected final String TEXT_994 = "_ESETFLAG) != 0;";

	protected final String TEXT_995 = this.NL + "\t\t\t";

	protected final String TEXT_996 = " &= ~";

	protected final String TEXT_997 = "_ESETFLAG;";

	protected final String TEXT_998 = this.NL + "\t\t\tboolean old";

	protected final String TEXT_999 = "ESet = ";

	protected final String TEXT_1000 = "ESet;";

	protected final String TEXT_1001 = this.NL + "\t\t\t";

	protected final String TEXT_1002 = "ESet = false;";

	protected final String TEXT_1003 = this.NL + "\t\t\tif (eNotificationRequired())" + this.NL + "\t\t\t\teNotify(new ";

	protected final String TEXT_1004 = "(this, ";

	protected final String TEXT_1005 = ".UNSET, ";

	protected final String TEXT_1006 = ", null, null, old";

	protected final String TEXT_1007 = "ESet));";

	protected final String TEXT_1008 = this.NL + "\t\t}";

	protected final String TEXT_1009 = this.NL + "\t\t";

	protected final String TEXT_1010 = " old";

	protected final String TEXT_1011 = " = (";

	protected final String TEXT_1012 = " & ";

	protected final String TEXT_1013 = "_EFLAG) != 0;";

	protected final String TEXT_1014 = this.NL + "\t\t";

	protected final String TEXT_1015 = " old";

	protected final String TEXT_1016 = " = ";

	protected final String TEXT_1017 = "_EFLAG_VALUES[(";

	protected final String TEXT_1018 = " & ";

	protected final String TEXT_1019 = "_EFLAG) >>> ";

	protected final String TEXT_1020 = "_EFLAG_OFFSET];";

	protected final String TEXT_1021 = this.NL + "\t\tObject old";

	protected final String TEXT_1022 = " = eVirtualUnset(";

	protected final String TEXT_1023 = ");";

	protected final String TEXT_1024 = this.NL + "\t\t";

	protected final String TEXT_1025 = " old";

	protected final String TEXT_1026 = " = ";

	protected final String TEXT_1027 = ";";

	protected final String TEXT_1028 = this.NL + "\t\tboolean isSetChange = old";

	protected final String TEXT_1029 = " != EVIRTUAL_NO_VALUE;";

	protected final String TEXT_1030 = this.NL + "\t\tboolean old";

	protected final String TEXT_1031 = "ESet = (";

	protected final String TEXT_1032 = " & ";

	protected final String TEXT_1033 = "_ESETFLAG) != 0;";

	protected final String TEXT_1034 = this.NL + "\t\tboolean old";

	protected final String TEXT_1035 = "ESet = ";

	protected final String TEXT_1036 = "ESet;";

	protected final String TEXT_1037 = this.NL + "\t\t";

	protected final String TEXT_1038 = " = null;";

	protected final String TEXT_1039 = this.NL + "\t\t";

	protected final String TEXT_1040 = " &= ~";

	protected final String TEXT_1041 = "_ESETFLAG;";

	protected final String TEXT_1042 = this.NL + "\t\t";

	protected final String TEXT_1043 = "ESet = false;";

	protected final String TEXT_1044 = this.NL + "\t\tif (eNotificationRequired())" + this.NL + "\t\t\teNotify(new ";

	protected final String TEXT_1045 = "(this, ";

	protected final String TEXT_1046 = ".UNSET, ";

	protected final String TEXT_1047 = ", ";

	protected final String TEXT_1048 = "isSetChange ? old";

	protected final String TEXT_1049 = " : null";

	protected final String TEXT_1050 = "old";

	protected final String TEXT_1051 = ", null, ";

	protected final String TEXT_1052 = "isSetChange";

	protected final String TEXT_1053 = "old";

	protected final String TEXT_1054 = "ESet";

	protected final String TEXT_1055 = "));";

	protected final String TEXT_1056 = this.NL + "\t\tif (";

	protected final String TEXT_1057 = ") ";

	protected final String TEXT_1058 = " |= ";

	protected final String TEXT_1059 = "_EFLAG; else ";

	protected final String TEXT_1060 = " &= ~";

	protected final String TEXT_1061 = "_EFLAG;";

	protected final String TEXT_1062 = this.NL + "\t\t";

	protected final String TEXT_1063 = " = ";

	protected final String TEXT_1064 = " & ~";

	protected final String TEXT_1065 = "_EFLAG | ";

	protected final String TEXT_1066 = "_EFLAG_DEFAULT;";

	protected final String TEXT_1067 = this.NL + "\t\t";

	protected final String TEXT_1068 = " = ";

	protected final String TEXT_1069 = ";";

	protected final String TEXT_1070 = this.NL + "\t\t";

	protected final String TEXT_1071 = " &= ~";

	protected final String TEXT_1072 = "_ESETFLAG;";

	protected final String TEXT_1073 = this.NL + "\t\t";

	protected final String TEXT_1074 = "ESet = false;";

	protected final String TEXT_1075 = this.NL + "\t\tif (eNotificationRequired())" + this.NL + "\t\t\teNotify(new ";

	protected final String TEXT_1076 = "(this, ";

	protected final String TEXT_1077 = ".UNSET, ";

	protected final String TEXT_1078 = ", ";

	protected final String TEXT_1079 = "isSetChange ? old";

	protected final String TEXT_1080 = " : ";

	protected final String TEXT_1081 = "old";

	protected final String TEXT_1082 = ", ";

	protected final String TEXT_1083 = ", ";

	protected final String TEXT_1084 = "isSetChange";

	protected final String TEXT_1085 = "old";

	protected final String TEXT_1086 = "ESet";

	protected final String TEXT_1087 = "));";

	protected final String TEXT_1088 = this.NL + "\t\t((";

	protected final String TEXT_1089 = ".Internal)((";

	protected final String TEXT_1090 = ".Internal.Wrapper)get";

	protected final String TEXT_1091 = "()).featureMap()).clear(";

	protected final String TEXT_1092 = ");";

	protected final String TEXT_1093 = this.NL + "\t\t((";

	protected final String TEXT_1094 = ".Internal)get";

	protected final String TEXT_1095 = "()).clear(";

	protected final String TEXT_1096 = ");";

	protected final String TEXT_1097 = this.NL + "\t\t";

	protected final String TEXT_1098 = this.NL + "\t\t// TODO: implement this method to unset the '";

	protected final String TEXT_1099 = "' ";

	protected final String TEXT_1100 = this.NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + this.NL
			+ "\t\tthrow new UnsupportedOperationException();";

	protected final String TEXT_1101 = this.NL + "\t}" + this.NL;

	protected final String TEXT_1102 = this.NL + "\t/**" + this.NL + "\t * Returns whether the value of the '{@link ";

	protected final String TEXT_1103 = "#";

	protected final String TEXT_1104 = " <em>";

	protected final String TEXT_1105 = "</em>}' ";

	protected final String TEXT_1106 = " is set.";

	protected final String TEXT_1107 = this.NL + "\t * <!-- begin-user-doc -->" + this.NL + "\t * <!-- end-user-doc -->" + this.NL
			+ "\t * @return whether the value of the '<em>";

	protected final String TEXT_1108 = "</em>' ";

	protected final String TEXT_1109 = " is set.";

	protected final String TEXT_1110 = this.NL + "\t * @see #unset";

	protected final String TEXT_1111 = "()";

	protected final String TEXT_1112 = this.NL + "\t * @see #";

	protected final String TEXT_1113 = "()";

	protected final String TEXT_1114 = this.NL + "\t * @see #set";

	protected final String TEXT_1115 = "(";

	protected final String TEXT_1116 = ")";

	protected final String TEXT_1117 = this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_1118 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_1119 = this.NL + "\tboolean isSet";

	protected final String TEXT_1120 = "();" + this.NL;

	protected final String TEXT_1121 = this.NL + "\tpublic boolean isSet";

	protected final String TEXT_1122 = "_";

	protected final String TEXT_1123 = "()" + this.NL + "\t{";

	protected final String TEXT_1124 = this.NL + "\t\treturn eDynamicIsSet(";

	protected final String TEXT_1125 = ", ";

	protected final String TEXT_1126 = ");";

	protected final String TEXT_1127 = this.NL + "\t\treturn eIsSet(";

	protected final String TEXT_1128 = ");";

	protected final String TEXT_1129 = this.NL + "\t\treturn ";

	protected final String TEXT_1130 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";

	protected final String TEXT_1131 = this.NL + "\t\t";

	protected final String TEXT_1132 = " ";

	protected final String TEXT_1133 = " = (";

	protected final String TEXT_1134 = ")eVirtualGet(";

	protected final String TEXT_1135 = ");";

	protected final String TEXT_1136 = this.NL + "\t\treturn ";

	protected final String TEXT_1137 = " != null && ((";

	protected final String TEXT_1138 = ".Unsettable";

	protected final String TEXT_1139 = ")";

	protected final String TEXT_1140 = ").isSet();";

	protected final String TEXT_1141 = this.NL + "\t\treturn eVirtualIsSet(";

	protected final String TEXT_1142 = ");";

	protected final String TEXT_1143 = this.NL + "\t\treturn (";

	protected final String TEXT_1144 = " & ";

	protected final String TEXT_1145 = "_ESETFLAG) != 0;";

	protected final String TEXT_1146 = this.NL + "\t\treturn ";

	protected final String TEXT_1147 = "ESet;";

	protected final String TEXT_1148 = this.NL + "\t\treturn !((";

	protected final String TEXT_1149 = ".Internal)((";

	protected final String TEXT_1150 = ".Internal.Wrapper)get";

	protected final String TEXT_1151 = "()).featureMap()).isEmpty(";

	protected final String TEXT_1152 = ");";

	protected final String TEXT_1153 = this.NL + "\t\treturn !((";

	protected final String TEXT_1154 = ".Internal)get";

	protected final String TEXT_1155 = "()).isEmpty(";

	protected final String TEXT_1156 = ");";

	protected final String TEXT_1157 = this.NL + "\t\t";

	protected final String TEXT_1158 = this.NL + "\t\t// TODO: implement this method to return whether the '";

	protected final String TEXT_1159 = "' ";

	protected final String TEXT_1160 = " is set" + this.NL
			+ "\t\t// Ensure that you remove @generated or mark it @generated NOT" + this.NL
			+ "\t\tthrow new UnsupportedOperationException();";

	protected final String TEXT_1161 = this.NL + "\t}" + this.NL;

	protected final String TEXT_1162 = this.NL + "\t/**" + this.NL + "\t * The cached validation expression for the '{@link #";

	protected final String TEXT_1163 = "(";

	protected final String TEXT_1164 = ") <em>";

	protected final String TEXT_1165 = "</em>}' invariant operation." + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @see #";

	protected final String TEXT_1166 = "(";

	protected final String TEXT_1167 = ")" + this.NL + "\t * @generated" + this.NL + "\t * @ordered" + this.NL + "\t */" + this.NL
			+ "\tprotected static final ";

	protected final String TEXT_1168 = " ";

	protected final String TEXT_1169 = "__EEXPRESSION = \"";

	protected final String TEXT_1170 = "\";";

	protected final String TEXT_1171 = this.NL;

	protected final String TEXT_1172 = this.NL + "\t/**" + this.NL + "\t * The cached invocation delegate for the '{@link #";

	protected final String TEXT_1173 = "(";

	protected final String TEXT_1174 = ") <em>";

	protected final String TEXT_1175 = "</em>}' operation." + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @see #";

	protected final String TEXT_1176 = "(";

	protected final String TEXT_1177 = ")" + this.NL + "\t * @generated" + this.NL + "\t * @ordered" + this.NL + "\t */" + this.NL
			+ "\tprotected static final ";

	protected final String TEXT_1178 = ".Internal.InvocationDelegate ";

	protected final String TEXT_1179 = "__EINVOCATION_DELEGATE = ((";

	protected final String TEXT_1180 = ".Internal)";

	protected final String TEXT_1181 = ").getInvocationDelegate();" + this.NL;

	protected final String TEXT_1182 = this.NL + "\t/**";

	protected final String TEXT_1183 = this.NL + "\t * <!-- begin-user-doc -->" + this.NL + "\t * <!-- end-user-doc -->";

	protected final String TEXT_1184 = this.NL + "\t * <!-- begin-model-doc -->";

	protected final String TEXT_1185 = this.NL + "\t * ";

	protected final String TEXT_1186 = this.NL + "\t * @param ";

	protected final String TEXT_1187 = this.NL + "\t *   ";

	protected final String TEXT_1188 = this.NL + "\t * @param ";

	protected final String TEXT_1189 = " ";

	protected final String TEXT_1190 = this.NL + "\t * <!-- end-model-doc -->";

	protected final String TEXT_1191 = this.NL + "\t * @model ";

	protected final String TEXT_1192 = this.NL + "\t *        ";

	protected final String TEXT_1193 = this.NL + "\t * @model";

	protected final String TEXT_1194 = this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_1195 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_1196 = this.NL + "\t";

	protected final String TEXT_1197 = " ";

	protected final String TEXT_1198 = "(";

	protected final String TEXT_1199 = ")";

	protected final String TEXT_1200 = ";" + this.NL;

	protected final String TEXT_1201 = this.NL + "\t@SuppressWarnings(\"unchecked\")";

	protected final String TEXT_1202 = this.NL + "\tpublic ";

	protected final String TEXT_1203 = " ";

	protected final String TEXT_1204 = "(";

	protected final String TEXT_1205 = ")";

	protected final String TEXT_1206 = this.NL + "\t{";

	protected final String TEXT_1207 = this.NL + "\t\t";

	protected final String TEXT_1208 = this.NL + "\t\treturn" + this.NL + "\t\t\t";

	protected final String TEXT_1209 = ".validate" + this.NL + "\t\t\t\t(";

	protected final String TEXT_1210 = "," + this.NL + "\t\t\t\t this," + this.NL + "\t\t\t\t ";

	protected final String TEXT_1211 = "," + this.NL + "\t\t\t\t ";

	protected final String TEXT_1212 = "," + this.NL + "\t\t\t\t \"";

	protected final String TEXT_1213 = "\",";

	protected final String TEXT_1214 = this.NL + "\t\t\t\t ";

	protected final String TEXT_1215 = "," + this.NL + "\t\t\t\t ";

	protected final String TEXT_1216 = "__EEXPRESSION," + this.NL + "\t\t\t\t ";

	protected final String TEXT_1217 = ".ERROR," + this.NL + "\t\t\t\t ";

	protected final String TEXT_1218 = ".DIAGNOSTIC_SOURCE," + this.NL + "\t\t\t\t ";

	protected final String TEXT_1219 = ".";

	protected final String TEXT_1220 = ");";

	protected final String TEXT_1221 = this.NL + "\t\t// TODO: implement this method" + this.NL
			+ "\t\t// -> specify the condition that violates the invariant" + this.NL
			+ "\t\t// -> verify the details of the diagnostic, including severity and message" + this.NL
			+ "\t\t// Ensure that you remove @generated or mark it @generated NOT" + this.NL + "\t\tif (false)" + this.NL
			+ "\t\t{" + this.NL + "\t\t\tif (";

	protected final String TEXT_1222 = " != null)" + this.NL + "\t\t\t{" + this.NL + "\t\t\t\t";

	protected final String TEXT_1223 = ".add" + this.NL + "\t\t\t\t\t(new ";

	protected final String TEXT_1224 = this.NL + "\t\t\t\t\t\t(";

	protected final String TEXT_1225 = ".ERROR," + this.NL + "\t\t\t\t\t\t ";

	protected final String TEXT_1226 = ".DIAGNOSTIC_SOURCE," + this.NL + "\t\t\t\t\t\t ";

	protected final String TEXT_1227 = ".";

	protected final String TEXT_1228 = "," + this.NL + "\t\t\t\t\t\t ";

	protected final String TEXT_1229 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";

	protected final String TEXT_1230 = "\", ";

	protected final String TEXT_1231 = ".getObjectLabel(this, ";

	protected final String TEXT_1232 = ") }),";

	protected final String TEXT_1233 = this.NL + "\t\t\t\t\t\t new Object [] { this }));" + this.NL + "\t\t\t}" + this.NL
			+ "\t\t\treturn false;" + this.NL + "\t\t}" + this.NL + "\t\treturn true;";

	protected final String TEXT_1234 = this.NL + "\t\ttry" + this.NL + "\t\t{";

	protected final String TEXT_1235 = this.NL + "\t\t\t";

	protected final String TEXT_1236 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";

	protected final String TEXT_1237 = "new ";

	protected final String TEXT_1238 = ".UnmodifiableEList<Object>(";

	protected final String TEXT_1239 = ", ";

	protected final String TEXT_1240 = ")";

	protected final String TEXT_1241 = "null";

	protected final String TEXT_1242 = ");";

	protected final String TEXT_1243 = this.NL + "\t\t\treturn ";

	protected final String TEXT_1244 = "(";

	protected final String TEXT_1245 = "(";

	protected final String TEXT_1246 = ")";

	protected final String TEXT_1247 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";

	protected final String TEXT_1248 = "new ";

	protected final String TEXT_1249 = ".UnmodifiableEList<Object>(";

	protected final String TEXT_1250 = ", ";

	protected final String TEXT_1251 = ")";

	protected final String TEXT_1252 = "null";

	protected final String TEXT_1253 = ")";

	protected final String TEXT_1254 = ").";

	protected final String TEXT_1255 = "()";

	protected final String TEXT_1256 = ";";

	protected final String TEXT_1257 = this.NL + "\t\t}" + this.NL + "\t\tcatch (";

	protected final String TEXT_1258 = " ite)" + this.NL + "\t\t{" + this.NL + "\t\t\tthrow new ";

	protected final String TEXT_1259 = "(ite);" + this.NL + "\t\t}";

	protected final String TEXT_1260 = this.NL + "\t\t// TODO: implement this method" + this.NL
			+ "\t\t// Ensure that you remove @generated or mark it @generated NOT" + this.NL
			+ "\t\tthrow new UnsupportedOperationException();";

	protected final String TEXT_1261 = this.NL + "\t}" + this.NL;

	protected final String TEXT_1262 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_1263 = this.NL + "\t@SuppressWarnings(\"unchecked\")";

	protected final String TEXT_1264 = this.NL + "\t@Override";

	protected final String TEXT_1265 = this.NL + "\tpublic ";

	protected final String TEXT_1266 = " eInverseAdd(";

	protected final String TEXT_1267 = " otherEnd, int featureID, ";

	protected final String TEXT_1268 = " msgs)" + this.NL + "\t{" + this.NL + "\t\tswitch (featureID";

	protected final String TEXT_1269 = ")" + this.NL + "\t\t{";

	protected final String TEXT_1270 = this.NL + "\t\t\tcase ";

	protected final String TEXT_1271 = ":";

	protected final String TEXT_1272 = this.NL + "\t\t\t\treturn ((";

	protected final String TEXT_1273 = "(";

	protected final String TEXT_1274 = ".InternalMapView";

	protected final String TEXT_1275 = ")";

	protected final String TEXT_1276 = "()).eMap()).basicAdd(otherEnd, msgs);";

	protected final String TEXT_1277 = this.NL + "\t\t\t\treturn (";

	protected final String TEXT_1278 = "()).basicAdd(otherEnd, msgs);";

	protected final String TEXT_1279 = this.NL + "\t\t\t\tif (eInternalContainer() != null)" + this.NL
			+ "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);";

	protected final String TEXT_1280 = this.NL + "\t\t\t\treturn basicSet";

	protected final String TEXT_1281 = "((";

	protected final String TEXT_1282 = ")otherEnd, msgs);";

	protected final String TEXT_1283 = this.NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";

	protected final String TEXT_1284 = ", msgs);";

	protected final String TEXT_1285 = this.NL + "\t\t\t\t";

	protected final String TEXT_1286 = " ";

	protected final String TEXT_1287 = " = (";

	protected final String TEXT_1288 = ")eVirtualGet(";

	protected final String TEXT_1289 = ");";

	protected final String TEXT_1290 = this.NL + "\t\t\t\t";

	protected final String TEXT_1291 = " ";

	protected final String TEXT_1292 = " = ";

	protected final String TEXT_1293 = "basicGet";

	protected final String TEXT_1294 = "();";

	protected final String TEXT_1295 = this.NL + "\t\t\t\tif (";

	protected final String TEXT_1296 = " != null)";

	protected final String TEXT_1297 = this.NL + "\t\t\t\t\tmsgs = ((";

	protected final String TEXT_1298 = ")";

	protected final String TEXT_1299 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";

	protected final String TEXT_1300 = ", null, msgs);";

	protected final String TEXT_1301 = this.NL + "\t\t\t\t\tmsgs = ((";

	protected final String TEXT_1302 = ")";

	protected final String TEXT_1303 = ").eInverseRemove(this, ";

	protected final String TEXT_1304 = ", ";

	protected final String TEXT_1305 = ".class, msgs);";

	protected final String TEXT_1306 = this.NL + "\t\t\t\treturn basicSet";

	protected final String TEXT_1307 = "((";

	protected final String TEXT_1308 = ")otherEnd, msgs);";

	protected final String TEXT_1309 = this.NL + "\t\t}";

	protected final String TEXT_1310 = this.NL + "\t\treturn super.eInverseAdd(otherEnd, featureID, msgs);";

	protected final String TEXT_1311 = this.NL + "\t\treturn eDynamicInverseAdd(otherEnd, featureID, msgs);";

	protected final String TEXT_1312 = this.NL + "\t}" + this.NL;

	protected final String TEXT_1313 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_1314 = this.NL + "\t@Override";

	protected final String TEXT_1315 = this.NL + "\tpublic ";

	protected final String TEXT_1316 = " eInverseRemove(";

	protected final String TEXT_1317 = " otherEnd, int featureID, ";

	protected final String TEXT_1318 = " msgs)" + this.NL + "\t{" + this.NL + "\t\tswitch (featureID";

	protected final String TEXT_1319 = ")" + this.NL + "\t\t{";

	protected final String TEXT_1320 = this.NL + "\t\t\tcase ";

	protected final String TEXT_1321 = ":";

	protected final String TEXT_1322 = this.NL + "\t\t\t\treturn ((";

	protected final String TEXT_1323 = ")((";

	protected final String TEXT_1324 = ".InternalMapView";

	protected final String TEXT_1325 = ")";

	protected final String TEXT_1326 = "()).eMap()).basicRemove(otherEnd, msgs);";

	protected final String TEXT_1327 = this.NL + "\t\t\t\treturn ((";

	protected final String TEXT_1328 = ")((";

	protected final String TEXT_1329 = ".Internal.Wrapper)";

	protected final String TEXT_1330 = "()).featureMap()).basicRemove(otherEnd, msgs);";

	protected final String TEXT_1331 = this.NL + "\t\t\t\treturn ((";

	protected final String TEXT_1332 = ")";

	protected final String TEXT_1333 = "()).basicRemove(otherEnd, msgs);";

	protected final String TEXT_1334 = this.NL + "\t\t\t\treturn eBasicSetContainer(null, ";

	protected final String TEXT_1335 = ", msgs);";

	protected final String TEXT_1336 = this.NL + "\t\t\t\treturn basicUnset";

	protected final String TEXT_1337 = "(msgs);";

	protected final String TEXT_1338 = this.NL + "\t\t\t\treturn basicSet";

	protected final String TEXT_1339 = "(null, msgs);";

	protected final String TEXT_1340 = this.NL + "\t\t}";

	protected final String TEXT_1341 = this.NL + "\t\treturn super.eInverseRemove(otherEnd, featureID, msgs);";

	protected final String TEXT_1342 = this.NL + "\t\treturn eDynamicInverseRemove(otherEnd, featureID, msgs);";

	protected final String TEXT_1343 = this.NL + "\t}" + this.NL;

	protected final String TEXT_1344 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_1345 = this.NL + "\t@Override";

	protected final String TEXT_1346 = this.NL + "\tpublic ";

	protected final String TEXT_1347 = " eBasicRemoveFromContainerFeature(";

	protected final String TEXT_1348 = " msgs)" + this.NL + "\t{" + this.NL + "\t\tswitch (eContainerFeatureID()";

	protected final String TEXT_1349 = ")" + this.NL + "\t\t{";

	protected final String TEXT_1350 = this.NL + "\t\t\tcase ";

	protected final String TEXT_1351 = ":" + this.NL + "\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";

	protected final String TEXT_1352 = ", ";

	protected final String TEXT_1353 = ".class, msgs);";

	protected final String TEXT_1354 = this.NL + "\t\t}";

	protected final String TEXT_1355 = this.NL + "\t\treturn super.eBasicRemoveFromContainerFeature(msgs);";

	protected final String TEXT_1356 = this.NL + "\t\treturn eDynamicBasicRemoveFromContainer(msgs);";

	protected final String TEXT_1357 = this.NL + "\t}" + this.NL;

	protected final String TEXT_1358 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_1359 = this.NL + "\t@Override";

	protected final String TEXT_1360 = this.NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)"
			+ this.NL + "\t{" + this.NL + "\t\tswitch (featureID";

	protected final String TEXT_1361 = ")" + this.NL + "\t\t{";

	protected final String TEXT_1362 = this.NL + "\t\t\tcase ";

	protected final String TEXT_1363 = ":";

	protected final String TEXT_1364 = this.NL + "\t\t\t\treturn ";

	protected final String TEXT_1365 = "();";

	protected final String TEXT_1366 = this.NL + "\t\t\t\treturn ";

	protected final String TEXT_1367 = "() ? Boolean.TRUE : Boolean.FALSE;";

	protected final String TEXT_1368 = this.NL + "\t\t\t\treturn new ";

	protected final String TEXT_1369 = "(";

	protected final String TEXT_1370 = "());";

	protected final String TEXT_1371 = this.NL + "\t\t\t\tif (resolve) return ";

	protected final String TEXT_1372 = "();" + this.NL + "\t\t\t\treturn basicGet";

	protected final String TEXT_1373 = "();";

	protected final String TEXT_1374 = this.NL + "\t\t\t\tif (coreType) return ((";

	protected final String TEXT_1375 = ".InternalMapView";

	protected final String TEXT_1376 = ")";

	protected final String TEXT_1377 = "()).eMap();" + this.NL + "\t\t\t\telse return ";

	protected final String TEXT_1378 = "();";

	protected final String TEXT_1379 = this.NL + "\t\t\t\tif (coreType) return ";

	protected final String TEXT_1380 = "();" + this.NL + "\t\t\t\telse return ";

	protected final String TEXT_1381 = "().map();";

	protected final String TEXT_1382 = this.NL + "\t\t\t\tif (coreType) return ((";

	protected final String TEXT_1383 = ".Internal.Wrapper)";

	protected final String TEXT_1384 = "()).featureMap();" + this.NL + "\t\t\t\treturn ";

	protected final String TEXT_1385 = "();";

	protected final String TEXT_1386 = this.NL + "\t\t\t\tif (coreType) return ";

	protected final String TEXT_1387 = "();" + this.NL + "\t\t\t\treturn ((";

	protected final String TEXT_1388 = ".Internal)";

	protected final String TEXT_1389 = "()).getWrapper();";

	protected final String TEXT_1390 = this.NL + "\t\t\t\treturn ";

	protected final String TEXT_1391 = "();";

	protected final String TEXT_1392 = this.NL + "\t\t}";

	protected final String TEXT_1393 = this.NL + "\t\treturn super.eGet(featureID, resolve, coreType);";

	protected final String TEXT_1394 = this.NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";

	protected final String TEXT_1395 = this.NL + "\t}" + this.NL;

	protected final String TEXT_1396 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_1397 = this.NL + "\t@SuppressWarnings(\"unchecked\")";

	protected final String TEXT_1398 = this.NL + "\t@Override";

	protected final String TEXT_1399 = this.NL + "\tpublic void eSet(int featureID, Object newValue)" + this.NL + "\t{" + this.NL
			+ "\t\tswitch (featureID";

	protected final String TEXT_1400 = ")" + this.NL + "\t\t{";

	protected final String TEXT_1401 = this.NL + "\t\t\tcase ";

	protected final String TEXT_1402 = ":";

	protected final String TEXT_1403 = this.NL + "\t\t\t\t((";

	protected final String TEXT_1404 = ".Internal)((";

	protected final String TEXT_1405 = ".Internal.Wrapper)";

	protected final String TEXT_1406 = "()).featureMap()).set(newValue);";

	protected final String TEXT_1407 = this.NL + "\t\t\t\t((";

	protected final String TEXT_1408 = ".Internal)";

	protected final String TEXT_1409 = "()).set(newValue);";

	protected final String TEXT_1410 = this.NL + "\t\t\t\t((";

	protected final String TEXT_1411 = ".Setting)((";

	protected final String TEXT_1412 = ".InternalMapView";

	protected final String TEXT_1413 = ")";

	protected final String TEXT_1414 = "()).eMap()).set(newValue);";

	protected final String TEXT_1415 = this.NL + "\t\t\t\t((";

	protected final String TEXT_1416 = ".Setting)";

	protected final String TEXT_1417 = "()).set(newValue);";

	protected final String TEXT_1418 = this.NL + "\t\t\t\t";

	protected final String TEXT_1419 = "().clear();" + this.NL + "\t\t\t\t";

	protected final String TEXT_1420 = "().addAll((";

	protected final String TEXT_1421 = "<? extends ";

	protected final String TEXT_1422 = ">";

	protected final String TEXT_1423 = ")newValue);";

	protected final String TEXT_1424 = this.NL + "\t\t\t\tset";

	protected final String TEXT_1425 = "(((";

	protected final String TEXT_1426 = ")newValue).";

	protected final String TEXT_1427 = "());";

	protected final String TEXT_1428 = this.NL + "\t\t\t\tset";

	protected final String TEXT_1429 = "(";

	protected final String TEXT_1430 = "(";

	protected final String TEXT_1431 = ")";

	protected final String TEXT_1432 = "newValue);";

	protected final String TEXT_1433 = this.NL + "\t\t\t\treturn;";

	protected final String TEXT_1434 = this.NL + "\t\t}";

	protected final String TEXT_1435 = this.NL + "\t\tsuper.eSet(featureID, newValue);";

	protected final String TEXT_1436 = this.NL + "\t\teDynamicSet(featureID, newValue);";

	protected final String TEXT_1437 = this.NL + "\t}" + this.NL;

	protected final String TEXT_1438 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_1439 = this.NL + "\t@Override";

	protected final String TEXT_1440 = this.NL + "\tpublic void eUnset(int featureID)" + this.NL + "\t{" + this.NL
			+ "\t\tswitch (featureID";

	protected final String TEXT_1441 = ")" + this.NL + "\t\t{";

	protected final String TEXT_1442 = this.NL + "\t\t\tcase ";

	protected final String TEXT_1443 = ":";

	protected final String TEXT_1444 = this.NL + "\t\t\t\t((";

	protected final String TEXT_1445 = ".Internal.Wrapper)";

	protected final String TEXT_1446 = "()).featureMap().clear();";

	protected final String TEXT_1447 = this.NL + "\t\t\t\t";

	protected final String TEXT_1448 = "().clear();";

	protected final String TEXT_1449 = this.NL + "\t\t\t\tunset";

	protected final String TEXT_1450 = "();";

	protected final String TEXT_1451 = this.NL + "\t\t\t\tset";

	protected final String TEXT_1452 = "((";

	protected final String TEXT_1453 = ")null);";

	protected final String TEXT_1454 = this.NL + "\t\t\t\tset";

	protected final String TEXT_1455 = "(";

	protected final String TEXT_1456 = ");";

	protected final String TEXT_1457 = this.NL + "\t\t\t\treturn;";

	protected final String TEXT_1458 = this.NL + "\t\t}";

	protected final String TEXT_1459 = this.NL + "\t\tsuper.eUnset(featureID);";

	protected final String TEXT_1460 = this.NL + "\t\teDynamicUnset(featureID);";

	protected final String TEXT_1461 = this.NL + "\t}" + this.NL;

	protected final String TEXT_1462 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_1463 = this.NL + "\t@SuppressWarnings(\"unchecked\")";

	protected final String TEXT_1464 = this.NL + "\t@Override";

	protected final String TEXT_1465 = this.NL + "\tpublic boolean eIsSet(int featureID)" + this.NL + "\t{" + this.NL
			+ "\t\tswitch (featureID";

	protected final String TEXT_1466 = ")" + this.NL + "\t\t{";

	protected final String TEXT_1467 = this.NL + "\t\t\tcase ";

	protected final String TEXT_1468 = ":";

	protected final String TEXT_1469 = this.NL + "\t\t\t\treturn isSet";

	protected final String TEXT_1470 = "();";

	protected final String TEXT_1471 = this.NL + "\t\t\t\treturn ";

	protected final String TEXT_1472 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";

	protected final String TEXT_1473 = this.NL + "\t\t\t\treturn !((";

	protected final String TEXT_1474 = ".Internal.Wrapper)";

	protected final String TEXT_1475 = "()).featureMap().isEmpty();";

	protected final String TEXT_1476 = this.NL + "\t\t\t\treturn ";

	protected final String TEXT_1477 = " != null && !";

	protected final String TEXT_1478 = ".featureMap().isEmpty();";

	protected final String TEXT_1479 = this.NL + "\t\t\t\treturn ";

	protected final String TEXT_1480 = " != null && !";

	protected final String TEXT_1481 = ".isEmpty();";

	protected final String TEXT_1482 = this.NL + "\t\t\t\t";

	protected final String TEXT_1483 = " ";

	protected final String TEXT_1484 = " = (";

	protected final String TEXT_1485 = ")eVirtualGet(";

	protected final String TEXT_1486 = ");" + this.NL + "\t\t\t\treturn ";

	protected final String TEXT_1487 = " != null && !";

	protected final String TEXT_1488 = ".isEmpty();";

	protected final String TEXT_1489 = this.NL + "\t\t\t\treturn !";

	protected final String TEXT_1490 = "().isEmpty();";

	protected final String TEXT_1491 = this.NL + "\t\t\t\treturn isSet";

	protected final String TEXT_1492 = "();";

	protected final String TEXT_1493 = this.NL + "\t\t\t\treturn ";

	protected final String TEXT_1494 = " != null;";

	protected final String TEXT_1495 = this.NL + "\t\t\t\treturn eVirtualGet(";

	protected final String TEXT_1496 = ") != null;";

	protected final String TEXT_1497 = this.NL + "\t\t\t\treturn basicGet";

	protected final String TEXT_1498 = "() != null;";

	protected final String TEXT_1499 = this.NL + "\t\t\t\treturn ";

	protected final String TEXT_1500 = " != null;";

	protected final String TEXT_1501 = this.NL + "\t\t\t\treturn eVirtualGet(";

	protected final String TEXT_1502 = ") != null;";

	protected final String TEXT_1503 = this.NL + "\t\t\t\treturn ";

	protected final String TEXT_1504 = "() != null;";

	protected final String TEXT_1505 = this.NL + "\t\t\t\treturn ((";

	protected final String TEXT_1506 = " & ";

	protected final String TEXT_1507 = "_EFLAG) != 0) != ";

	protected final String TEXT_1508 = ";";

	protected final String TEXT_1509 = this.NL + "\t\t\t\treturn (";

	protected final String TEXT_1510 = " & ";

	protected final String TEXT_1511 = "_EFLAG) != ";

	protected final String TEXT_1512 = "_EFLAG_DEFAULT;";

	protected final String TEXT_1513 = this.NL + "\t\t\t\treturn ";

	protected final String TEXT_1514 = " != ";

	protected final String TEXT_1515 = ";";

	protected final String TEXT_1516 = this.NL + "\t\t\t\treturn eVirtualGet(";

	protected final String TEXT_1517 = ", ";

	protected final String TEXT_1518 = ") != ";

	protected final String TEXT_1519 = ";";

	protected final String TEXT_1520 = this.NL + "\t\t\t\treturn ";

	protected final String TEXT_1521 = "() != ";

	protected final String TEXT_1522 = ";";

	protected final String TEXT_1523 = this.NL + "\t\t\t\treturn ";

	protected final String TEXT_1524 = " == null ? ";

	protected final String TEXT_1525 = " != null : !";

	protected final String TEXT_1526 = ".equals(";

	protected final String TEXT_1527 = ");";

	protected final String TEXT_1528 = this.NL + "\t\t\t\t";

	protected final String TEXT_1529 = " ";

	protected final String TEXT_1530 = " = (";

	protected final String TEXT_1531 = ")eVirtualGet(";

	protected final String TEXT_1532 = ", ";

	protected final String TEXT_1533 = ");" + this.NL + "\t\t\t\treturn ";

	protected final String TEXT_1534 = " == null ? ";

	protected final String TEXT_1535 = " != null : !";

	protected final String TEXT_1536 = ".equals(";

	protected final String TEXT_1537 = ");";

	protected final String TEXT_1538 = this.NL + "\t\t\t\treturn ";

	protected final String TEXT_1539 = " == null ? ";

	protected final String TEXT_1540 = "() != null : !";

	protected final String TEXT_1541 = ".equals(";

	protected final String TEXT_1542 = "());";

	protected final String TEXT_1543 = this.NL + "\t\t}";

	protected final String TEXT_1544 = this.NL + "\t\treturn super.eIsSet(featureID);";

	protected final String TEXT_1545 = this.NL + "\t\treturn eDynamicIsSet(featureID);";

	protected final String TEXT_1546 = this.NL + "\t}" + this.NL;

	protected final String TEXT_1547 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_1548 = this.NL + "\t@Override";

	protected final String TEXT_1549 = this.NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class";

	protected final String TEXT_1550 = " baseClass)" + this.NL + "\t{";

	protected final String TEXT_1551 = this.NL + "\t\tif (baseClass == ";

	protected final String TEXT_1552 = ".class)" + this.NL + "\t\t{" + this.NL + "\t\t\tswitch (derivedFeatureID";

	protected final String TEXT_1553 = ")" + this.NL + "\t\t\t{";

	protected final String TEXT_1554 = this.NL + "\t\t\t\tcase ";

	protected final String TEXT_1555 = ": return ";

	protected final String TEXT_1556 = ";";

	protected final String TEXT_1557 = this.NL + "\t\t\t\tdefault: return -1;" + this.NL + "\t\t\t}" + this.NL + "\t\t}";

	protected final String TEXT_1558 = this.NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);"
			+ this.NL + "\t}";

	protected final String TEXT_1559 = this.NL + this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_1560 = this.NL + "\t@Override";

	protected final String TEXT_1561 = this.NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class";

	protected final String TEXT_1562 = " baseClass)" + this.NL + "\t{";

	protected final String TEXT_1563 = this.NL + "\t\tif (baseClass == ";

	protected final String TEXT_1564 = ".class)" + this.NL + "\t\t{" + this.NL + "\t\t\tswitch (baseFeatureID)" + this.NL + "\t\t\t{";

	protected final String TEXT_1565 = this.NL + "\t\t\t\tcase ";

	protected final String TEXT_1566 = ": return ";

	protected final String TEXT_1567 = ";";

	protected final String TEXT_1568 = this.NL + "\t\t\t\tdefault: return -1;" + this.NL + "\t\t\t}" + this.NL + "\t\t}";

	protected final String TEXT_1569 = this.NL + "\t\tif (baseClass == ";

	protected final String TEXT_1570 = ".class)" + this.NL + "\t\t{" + this.NL + "\t\t\tswitch (baseFeatureID";

	protected final String TEXT_1571 = ")" + this.NL + "\t\t\t{";

	protected final String TEXT_1572 = this.NL + "\t\t\t\tcase ";

	protected final String TEXT_1573 = ": return ";

	protected final String TEXT_1574 = ";";

	protected final String TEXT_1575 = this.NL + "\t\t\t\tdefault: return -1;" + this.NL + "\t\t\t}" + this.NL + "\t\t}";

	protected final String TEXT_1576 = this.NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);"
			+ this.NL + "\t}" + this.NL;

	protected final String TEXT_1577 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_1578 = this.NL + "\t@Override";

	protected final String TEXT_1579 = this.NL + "\tpublic int eDerivedOperationID(int baseOperationID, Class";

	protected final String TEXT_1580 = " baseClass)" + this.NL + "\t{";

	protected final String TEXT_1581 = this.NL + "\t\tif (baseClass == ";

	protected final String TEXT_1582 = ".class)" + this.NL + "\t\t{" + this.NL + "\t\t\tswitch (baseOperationID)" + this.NL
			+ "\t\t\t{";

	protected final String TEXT_1583 = this.NL + "\t\t\t\tcase ";

	protected final String TEXT_1584 = ": return ";

	protected final String TEXT_1585 = ";";

	protected final String TEXT_1586 = this.NL
			+ "\t\t\t\tdefault: return super.eDerivedOperationID(baseOperationID, baseClass);" + this.NL + "\t\t\t}" + this.NL
			+ "\t\t}";

	protected final String TEXT_1587 = this.NL + "\t\tif (baseClass == ";

	protected final String TEXT_1588 = ".class)" + this.NL + "\t\t{" + this.NL + "\t\t\tswitch (baseOperationID)" + this.NL
			+ "\t\t\t{";

	protected final String TEXT_1589 = this.NL + "\t\t\t\tcase ";

	protected final String TEXT_1590 = ": return ";

	protected final String TEXT_1591 = ";";

	protected final String TEXT_1592 = this.NL + "\t\t\t\tdefault: return -1;" + this.NL + "\t\t\t}" + this.NL + "\t\t}";

	protected final String TEXT_1593 = this.NL + "\t\tif (baseClass == ";

	protected final String TEXT_1594 = ".class)" + this.NL + "\t\t{" + this.NL + "\t\t\tswitch (baseOperationID";

	protected final String TEXT_1595 = ")" + this.NL + "\t\t\t{";

	protected final String TEXT_1596 = this.NL + "\t\t\t\tcase ";

	protected final String TEXT_1597 = ": return ";

	protected final String TEXT_1598 = ";";

	protected final String TEXT_1599 = this.NL + "\t\t\t\tdefault: return -1;" + this.NL + "\t\t\t}" + this.NL + "\t\t}";

	protected final String TEXT_1600 = this.NL + "\t\treturn super.eDerivedOperationID(baseOperationID, baseClass);" + this.NL
			+ "\t}" + this.NL;

	protected final String TEXT_1601 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_1602 = this.NL + "\t@Override";

	protected final String TEXT_1603 = this.NL + "\tprotected Object[] eVirtualValues()" + this.NL + "\t{" + this.NL + "\t\treturn ";

	protected final String TEXT_1604 = ";" + this.NL + "\t}" + this.NL + "" + this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->"
			+ this.NL + "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_1605 = this.NL + "\t@Override";

	protected final String TEXT_1606 = this.NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + this.NL + "\t{" + this.NL
			+ "\t\t";

	protected final String TEXT_1607 = " = newValues;" + this.NL + "\t}" + this.NL;

	protected final String TEXT_1608 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_1609 = this.NL + "\t@Override";

	protected final String TEXT_1610 = this.NL + "\tprotected int eVirtualIndexBits(int offset)" + this.NL + "\t{" + this.NL
			+ "\t\tswitch (offset)" + this.NL + "\t\t{";

	protected final String TEXT_1611 = this.NL + "\t\t\tcase ";

	protected final String TEXT_1612 = " :" + this.NL + "\t\t\t\treturn ";

	protected final String TEXT_1613 = ";";

	protected final String TEXT_1614 = this.NL + "\t\t\tdefault :" + this.NL + "\t\t\t\tthrow new IndexOutOfBoundsException();"
			+ this.NL + "\t\t}" + this.NL + "\t}" + this.NL + "" + this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_1615 = this.NL + "\t@Override";

	protected final String TEXT_1616 = this.NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + this.NL
			+ "\t{" + this.NL + "\t\tswitch (offset)" + this.NL + "\t\t{";

	protected final String TEXT_1617 = this.NL + "\t\t\tcase ";

	protected final String TEXT_1618 = " :" + this.NL + "\t\t\t\t";

	protected final String TEXT_1619 = " = newIndexBits;" + this.NL + "\t\t\t\tbreak;";

	protected final String TEXT_1620 = this.NL + "\t\t\tdefault :" + this.NL + "\t\t\t\tthrow new IndexOutOfBoundsException();"
			+ this.NL + "\t\t}" + this.NL + "\t}" + this.NL;

	protected final String TEXT_1621 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_1622 = this.NL + "\t@Override";

	protected final String TEXT_1623 = this.NL + "\t@SuppressWarnings(";

	protected final String TEXT_1624 = "\"unchecked\"";

	protected final String TEXT_1625 = "{\"rawtypes\", \"unchecked\" }";

	protected final String TEXT_1626 = ")";

	protected final String TEXT_1627 = this.NL + "\tpublic Object eInvoke(int operationID, ";

	protected final String TEXT_1628 = " arguments) throws ";

	protected final String TEXT_1629 = this.NL + "\t{" + this.NL + "\t\tswitch (operationID";

	protected final String TEXT_1630 = ")" + this.NL + "\t\t{";

	protected final String TEXT_1631 = this.NL + "\t\t\tcase ";

	protected final String TEXT_1632 = ":";

	protected final String TEXT_1633 = this.NL + "\t\t\t\t";

	protected final String TEXT_1634 = "(";

	protected final String TEXT_1635 = "(";

	protected final String TEXT_1636 = "(";

	protected final String TEXT_1637 = ")";

	protected final String TEXT_1638 = "arguments.get(";

	protected final String TEXT_1639 = ")";

	protected final String TEXT_1640 = ").";

	protected final String TEXT_1641 = "()";

	protected final String TEXT_1642 = ", ";

	protected final String TEXT_1643 = ");" + this.NL + "\t\t\t\treturn null;";

	protected final String TEXT_1644 = this.NL + "\t\t\t\treturn ";

	protected final String TEXT_1645 = "new ";

	protected final String TEXT_1646 = "(";

	protected final String TEXT_1647 = "(";

	protected final String TEXT_1648 = "(";

	protected final String TEXT_1649 = "(";

	protected final String TEXT_1650 = ")";

	protected final String TEXT_1651 = "arguments.get(";

	protected final String TEXT_1652 = ")";

	protected final String TEXT_1653 = ").";

	protected final String TEXT_1654 = "()";

	protected final String TEXT_1655 = ", ";

	protected final String TEXT_1656 = ")";

	protected final String TEXT_1657 = ")";

	protected final String TEXT_1658 = ";";

	protected final String TEXT_1659 = this.NL + "\t\t}";

	protected final String TEXT_1660 = this.NL + "\t\treturn super.eInvoke(operationID, arguments);";

	protected final String TEXT_1661 = this.NL + "\t\treturn eDynamicInvoke(operationID, arguments);";

	protected final String TEXT_1662 = this.NL + "\t}" + this.NL;

	protected final String TEXT_1663 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_1664 = this.NL + "\t@Override";

	protected final String TEXT_1665 = this.NL + "\tpublic String toString()" + this.NL + "\t{" + this.NL
			+ "\t\tif (eIsProxy()) return super.toString();" + this.NL + "" + this.NL
			+ "\t\tStringBuffer result = new StringBuffer(super.toString());";

	protected final String TEXT_1666 = this.NL + "\t\tresult.append(\" (";

	protected final String TEXT_1667 = ": \");";

	protected final String TEXT_1668 = this.NL + "\t\tresult.append(\", ";

	protected final String TEXT_1669 = ": \");";

	protected final String TEXT_1670 = this.NL + "\t\tif (eVirtualIsSet(";

	protected final String TEXT_1671 = ")) result.append(eVirtualGet(";

	protected final String TEXT_1672 = ")); else result.append(\"<unset>\");";

	protected final String TEXT_1673 = this.NL + "\t\tif (";

	protected final String TEXT_1674 = "(";

	protected final String TEXT_1675 = " & ";

	protected final String TEXT_1676 = "_ESETFLAG) != 0";

	protected final String TEXT_1677 = "ESet";

	protected final String TEXT_1678 = ") result.append((";

	protected final String TEXT_1679 = " & ";

	protected final String TEXT_1680 = "_EFLAG) != 0); else result.append(\"<unset>\");";

	protected final String TEXT_1681 = this.NL + "\t\tif (";

	protected final String TEXT_1682 = "(";

	protected final String TEXT_1683 = " & ";

	protected final String TEXT_1684 = "_ESETFLAG) != 0";

	protected final String TEXT_1685 = "ESet";

	protected final String TEXT_1686 = ") result.append(";

	protected final String TEXT_1687 = "_EFLAG_VALUES[(";

	protected final String TEXT_1688 = " & ";

	protected final String TEXT_1689 = "_EFLAG) >>> ";

	protected final String TEXT_1690 = "_EFLAG_OFFSET]); else result.append(\"<unset>\");";

	protected final String TEXT_1691 = this.NL + "\t\tif (";

	protected final String TEXT_1692 = "(";

	protected final String TEXT_1693 = " & ";

	protected final String TEXT_1694 = "_ESETFLAG) != 0";

	protected final String TEXT_1695 = "ESet";

	protected final String TEXT_1696 = ") result.append(";

	protected final String TEXT_1697 = "); else result.append(\"<unset>\");";

	protected final String TEXT_1698 = this.NL + "\t\tresult.append(eVirtualGet(";

	protected final String TEXT_1699 = ", ";

	protected final String TEXT_1700 = "));";

	protected final String TEXT_1701 = this.NL + "\t\tresult.append((";

	protected final String TEXT_1702 = " & ";

	protected final String TEXT_1703 = "_EFLAG) != 0);";

	protected final String TEXT_1704 = this.NL + "\t\tresult.append(";

	protected final String TEXT_1705 = "_EFLAG_VALUES[(";

	protected final String TEXT_1706 = " & ";

	protected final String TEXT_1707 = "_EFLAG) >>> ";

	protected final String TEXT_1708 = "_EFLAG_OFFSET]);";

	protected final String TEXT_1709 = this.NL + "\t\tresult.append(";

	protected final String TEXT_1710 = ");";

	protected final String TEXT_1711 = this.NL + "\t\tresult.append(')');" + this.NL + "\t\treturn result.toString();" + this.NL
			+ "\t}" + this.NL;

	protected final String TEXT_1712 = this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */";

	protected final String TEXT_1713 = this.NL + "\t@";

	protected final String TEXT_1714 = this.NL + "\tprotected int hash = -1;" + this.NL + "" + this.NL + "\t/**" + this.NL
			+ "\t * <!-- begin-user-doc -->" + this.NL + "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */"
			+ this.NL + "\tpublic int getHash()" + this.NL + "\t{" + this.NL + "\t\tif (hash == -1)" + this.NL + "\t\t{" + this.NL + "\t\t\t";

	protected final String TEXT_1715 = " theKey = getKey();" + this.NL
			+ "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + this.NL + "\t\t}" + this.NL + "\t\treturn hash;" + this.NL
			+ "\t}" + this.NL + "" + this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL + "\t * <!-- end-user-doc -->"
			+ this.NL + "\t * @generated" + this.NL + "\t */" + this.NL + "\tpublic void setHash(int hash)" + this.NL + "\t{" + this.NL
			+ "\t\tthis.hash = hash;" + this.NL + "\t}" + this.NL + "" + this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */" + this.NL + "\tpublic ";

	protected final String TEXT_1716 = " getKey()" + this.NL + "\t{";

	protected final String TEXT_1717 = this.NL + "\t\treturn new ";

	protected final String TEXT_1718 = "(getTypedKey());";

	protected final String TEXT_1719 = this.NL + "\t\treturn getTypedKey();";

	protected final String TEXT_1720 = this.NL + "\t}" + this.NL + "" + this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */" + this.NL + "\tpublic void setKey(";

	protected final String TEXT_1721 = " key)" + this.NL + "\t{";

	protected final String TEXT_1722 = this.NL + "\t\tgetTypedKey().addAll(";

	protected final String TEXT_1723 = "(";

	protected final String TEXT_1724 = ")";

	protected final String TEXT_1725 = "key);";

	protected final String TEXT_1726 = this.NL + "\t\tsetTypedKey(key);";

	protected final String TEXT_1727 = this.NL + "\t\tsetTypedKey(((";

	protected final String TEXT_1728 = ")key).";

	protected final String TEXT_1729 = "());";

	protected final String TEXT_1730 = this.NL + "\t\tsetTypedKey((";

	protected final String TEXT_1731 = ")key);";

	protected final String TEXT_1732 = this.NL + "\t}" + this.NL + "" + this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */" + this.NL + "\tpublic ";

	protected final String TEXT_1733 = " getValue()" + this.NL + "\t{";

	protected final String TEXT_1734 = this.NL + "\t\treturn new ";

	protected final String TEXT_1735 = "(getTypedValue());";

	protected final String TEXT_1736 = this.NL + "\t\treturn getTypedValue();";

	protected final String TEXT_1737 = this.NL + "\t}" + this.NL + "" + this.NL + "\t/**" + this.NL + "\t * <!-- begin-user-doc -->" + this.NL
			+ "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL + "\t */" + this.NL + "\tpublic ";

	protected final String TEXT_1738 = " setValue(";

	protected final String TEXT_1739 = " value)" + this.NL + "\t{" + this.NL + "\t\t";

	protected final String TEXT_1740 = " oldValue = getValue();";

	protected final String TEXT_1741 = this.NL + "\t\tgetTypedValue().clear();" + this.NL + "\t\tgetTypedValue().addAll(";

	protected final String TEXT_1742 = "(";

	protected final String TEXT_1743 = ")";

	protected final String TEXT_1744 = "value);";

	protected final String TEXT_1745 = this.NL + "\t\tsetTypedValue(value);";

	protected final String TEXT_1746 = this.NL + "\t\tsetTypedValue(((";

	protected final String TEXT_1747 = ")value).";

	protected final String TEXT_1748 = "());";

	protected final String TEXT_1749 = this.NL + "\t\tsetTypedValue((";

	protected final String TEXT_1750 = ")value);";

	protected final String TEXT_1751 = this.NL + "\t\treturn oldValue;" + this.NL + "\t}" + this.NL + "" + this.NL + "\t/**" + this.NL
			+ "\t * <!-- begin-user-doc -->" + this.NL + "\t * <!-- end-user-doc -->" + this.NL + "\t * @generated" + this.NL
			+ "\t */";

	protected final String TEXT_1752 = this.NL + "\t@SuppressWarnings(\"unchecked\")";

	protected final String TEXT_1753 = this.NL + "\tpublic ";

	protected final String TEXT_1754 = " getEMap()" + this.NL + "\t{" + this.NL + "\t\t";

	protected final String TEXT_1755 = " container = eContainer();" + this.NL + "\t\treturn container == null ? null : (";

	protected final String TEXT_1756 = ")container.eGet(eContainmentFeature());" + this.NL + "\t}" + this.NL;

	protected final String TEXT_1757 = this.NL + "} //";

	protected final String TEXT_1758 = this.NL;

	public String generate(final Object argument) {
		final StringBuffer stringBuffer = new StringBuffer();

		/*
		 * Copyright (c) 2010-2012 Gergely Varro All rights reserved. This program and
		 * the accompanying materials are made available under the terms of the Eclipse
		 * Public License v1.0 which accompanies this distribution, and is available at
		 * http://www.eclipse.org/legal/epl-v10.html
		 *
		 * Contributors: Gergely Varro - Initial API and implementation
		 */

		/**
		 * Copyright (c) 2002-2011 IBM Corporation and others. All rights reserved. This
		 * program and the accompanying materials are made available under the terms of
		 * the Eclipse Public License v1.0 which accompanies this distribution, and is
		 * available at http://www.eclipse.org/legal/epl-v10.html
		 *
		 * Contributors: IBM - Initial API and implementation
		 */

		final GenClass genClass = (GenClass) ((Object[]) argument)[0];
		final GenPackage genPackage = genClass.getGenPackage();
		final GenModel genModel = genPackage.getGenModel();
		final boolean isJDK50 = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50;
		final boolean isInterface = Boolean.TRUE.equals(((Object[]) argument)[1]);
		final boolean isImplementation = Boolean.TRUE.equals(((Object[]) argument)[2]);
		final boolean isGWT = genModel.getRuntimePlatform() == GenRuntimePlatform.GWT;
		final String publicStaticFinalFlag = isImplementation ? "public static final " : "";
		final String singleWildcard = isJDK50 ? "<?>" : "";
		final String negativeOffsetCorrection = genClass.hasOffsetCorrection()
				? " - " + genClass.getOffsetCorrectionField(null)
				: "";
		final String positiveOffsetCorrection = genClass.hasOffsetCorrection()
				? " + " + genClass.getOffsetCorrectionField(null)
				: "";
		final String negativeOperationOffsetCorrection = genClass.hasOffsetCorrection()
				? " - EOPERATION_OFFSET_CORRECTION"
						: "";
		final String positiveOperationOffsetCorrection = genClass.hasOffsetCorrection()
				? " + EOPERATION_OFFSET_CORRECTION"
						: "";

		final AbstractMoflonClassGeneratorAdapter generatorAdapter = (AbstractMoflonClassGeneratorAdapter) ((Object[]) argument)[((Object[]) argument).length
		                                                                                                                         - 1];
		stringBuffer.append(this.TEXT_1);
		{
			final GenBase copyrightHolder = argument instanceof GenBase ? (GenBase) argument
					: (argument instanceof Object[]) && (((Object[]) argument)[0] instanceof GenBase)
					? (GenBase) ((Object[]) argument)[0]
							: null;
			if ((copyrightHolder != null) && copyrightHolder.hasCopyright()) {
				stringBuffer.append(this.TEXT_2);
				stringBuffer.append(
						copyrightHolder.getCopyright(copyrightHolder.getGenModel().getIndentation(stringBuffer)));
			}
		}
		stringBuffer.append(this.TEXT_3);
		if (isInterface) {
			stringBuffer.append(this.TEXT_4);
			stringBuffer.append(genPackage.getInterfacePackageName());
			stringBuffer.append(this.TEXT_5);
		} else {
			stringBuffer.append(this.TEXT_6);
			stringBuffer.append(genPackage.getClassPackageName());
			stringBuffer.append(this.TEXT_7);
		}
		stringBuffer.append(this.TEXT_8);
		genModel.markImportLocation(stringBuffer, genPackage);
		if (isImplementation) {
			genClass.addClassPsuedoImports();
		}

		stringBuffer.append(this.TEXT_9);
		if (isInterface) {
			stringBuffer.append(this.TEXT_10);
			stringBuffer.append(genClass.getFormattedName());
			stringBuffer.append(this.TEXT_11);
			if (genClass.hasDocumentation()) {
				stringBuffer.append(this.TEXT_12);
				stringBuffer.append(genClass.getDocumentation(genModel.getIndentation(stringBuffer)));
				stringBuffer.append(this.TEXT_13);
			}
			stringBuffer.append(this.TEXT_14);
			if (!genClass.getGenFeatures().isEmpty()) {
				stringBuffer.append(this.TEXT_15);
				for (final GenFeature genFeature : genClass.getGenFeatures()) {
					if (!genFeature.isSuppressedGetVisibility()) {
						stringBuffer.append(this.TEXT_16);
						stringBuffer.append(genClass.getQualifiedInterfaceName());
						stringBuffer.append(this.TEXT_17);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(this.TEXT_18);
						stringBuffer.append(genFeature.getFormattedName());
						stringBuffer.append(this.TEXT_19);
					}
				}
				stringBuffer.append(this.TEXT_20);
			}
			stringBuffer.append(this.TEXT_21);
			if (!genModel.isSuppressEMFMetaData()) {
				stringBuffer.append(this.TEXT_22);
				stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
				stringBuffer.append(this.TEXT_23);
				stringBuffer.append(genClass.getClassifierAccessorName());
				stringBuffer.append(this.TEXT_24);
			}
			if (!genModel.isSuppressEMFModelTags()) {
				boolean first = true;
				for (final StringTokenizer stringTokenizer = new StringTokenizer(genClass.getModelInfo(),
						"\n\r"); stringTokenizer.hasMoreTokens();) {
					final String modelInfo = stringTokenizer.nextToken();
					if (first) {
						first = false;
						stringBuffer.append(this.TEXT_25);
						stringBuffer.append(modelInfo);
					} else {
						stringBuffer.append(this.TEXT_26);
						stringBuffer.append(modelInfo);
					}
				}
				if (first) {
					stringBuffer.append(this.TEXT_27);
				}
			}
			if (genClass.needsRootExtendsInterfaceExtendsTag()) {
				stringBuffer.append(this.TEXT_28);
				stringBuffer.append(genModel.getImportedName(genModel.getRootExtendsInterface()));
			}
			stringBuffer.append(this.TEXT_29);
			// Class/interface.javadoc.override.javajetinc
		} else {
			stringBuffer.append(this.TEXT_30);
			stringBuffer.append(genClass.getFormattedName());
			stringBuffer.append(this.TEXT_31);
			if (!genClass.getImplementedGenFeatures().isEmpty()) {
				stringBuffer.append(this.TEXT_32);
				for (final GenFeature genFeature : genClass.getImplementedGenFeatures()) {
					stringBuffer.append(this.TEXT_33);
					stringBuffer.append(genClass.getQualifiedClassName());
					stringBuffer.append(this.TEXT_34);
					stringBuffer.append(genFeature.getGetAccessor());
					stringBuffer.append(this.TEXT_35);
					stringBuffer.append(genFeature.getFormattedName());
					stringBuffer.append(this.TEXT_36);
				}
				stringBuffer.append(this.TEXT_37);
			}
			stringBuffer.append(this.TEXT_38);
		}
		if (isImplementation) {
			stringBuffer.append(this.TEXT_39);
			if (genClass.isAbstract()) {
				stringBuffer.append(this.TEXT_40);
			}
			stringBuffer.append(this.TEXT_41);
			stringBuffer.append(genClass.getClassName());
			stringBuffer.append(genClass.getTypeParameters().trim());
			stringBuffer.append(genClass.getClassExtends());
			stringBuffer.append(genClass.getClassImplements());
		} else {
			stringBuffer.append(this.TEXT_42);
			stringBuffer.append(genClass.getInterfaceName());
			stringBuffer.append(genClass.getTypeParameters().trim());
			stringBuffer.append(genClass.getInterfaceExtends());
		}
		stringBuffer.append(this.TEXT_43);
		if (genModel.hasCopyrightField()) {
			stringBuffer.append(this.TEXT_44);
			stringBuffer.append(publicStaticFinalFlag);
			stringBuffer.append(genModel.getImportedName("java.lang.String"));
			stringBuffer.append(this.TEXT_45);
			stringBuffer.append(genModel.getCopyrightFieldLiteral());
			stringBuffer.append(this.TEXT_46);
			stringBuffer.append(genModel.getNonNLS());
			stringBuffer.append(this.TEXT_47);
		}
		if (isImplementation && (genModel.getDriverNumber() != null)) {
			stringBuffer.append(this.TEXT_48);
			stringBuffer.append(genModel.getImportedName("java.lang.String"));
			stringBuffer.append(this.TEXT_49);
			stringBuffer.append(genModel.getDriverNumber());
			stringBuffer.append(this.TEXT_50);
			stringBuffer.append(genModel.getNonNLS());
			stringBuffer.append(this.TEXT_51);
		}
		if (isImplementation && genClass.isJavaIOSerializable()) {
			stringBuffer.append(this.TEXT_52);
		}
		if (isImplementation && genModel.isVirtualDelegation()) {
			final String eVirtualValuesField = genClass.getEVirtualValuesField();
			if (eVirtualValuesField != null) {
				stringBuffer.append(this.TEXT_53);
				if (isGWT) {
					stringBuffer.append(this.TEXT_54);
					stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
				}
				stringBuffer.append(this.TEXT_55);
				stringBuffer.append(eVirtualValuesField);
				stringBuffer.append(this.TEXT_56);
			}
			{
				final List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
				if (!eVirtualIndexBitFields.isEmpty()) {
					for (final String eVirtualIndexBitField : eVirtualIndexBitFields) {
						stringBuffer.append(this.TEXT_57);
						if (isGWT) {
							stringBuffer.append(this.TEXT_58);
							stringBuffer
							.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
						}
						stringBuffer.append(this.TEXT_59);
						stringBuffer.append(eVirtualIndexBitField);
						stringBuffer.append(this.TEXT_60);
					}
				}
			}
		}
		if (isImplementation && genClass.isModelRoot() && genModel.isBooleanFlagsEnabled()
				&& (genModel.getBooleanFlagsReservedBits() == -1)) {
			stringBuffer.append(this.TEXT_61);
			if (isGWT) {
				stringBuffer.append(this.TEXT_62);
				stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
			}
			stringBuffer.append(this.TEXT_63);
			stringBuffer.append(genModel.getBooleanFlagsField());
			stringBuffer.append(this.TEXT_64);
		}
		if (isImplementation && !genModel.isReflectiveDelegation()) {
			for (final GenFeature genFeature : genClass.getDeclaredFieldGenFeatures()) {
				if (genFeature.hasSettingDelegate()) {
					stringBuffer.append(this.TEXT_65);
					stringBuffer.append(genFeature.getGetAccessor());
					stringBuffer.append(this.TEXT_66);
					stringBuffer.append(genFeature.getFormattedName());
					stringBuffer.append(this.TEXT_67);
					stringBuffer.append(genFeature.getFeatureKind());
					stringBuffer.append(this.TEXT_68);
					stringBuffer.append(genFeature.getGetAccessor());
					stringBuffer.append(this.TEXT_69);
					if (isGWT) {
						stringBuffer.append(this.TEXT_70);
						stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
					}
					stringBuffer.append(this.TEXT_71);
					stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
					stringBuffer.append(this.TEXT_72);
					stringBuffer.append(genFeature.getUpperName());
					stringBuffer.append(this.TEXT_73);
					stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
					stringBuffer.append(this.TEXT_74);
					stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
					stringBuffer.append(this.TEXT_75);
				} else if (genFeature.isListType() || genFeature.isReferenceType()) {
					if (genClass.isField(genFeature)) {
						stringBuffer.append(this.TEXT_76);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(this.TEXT_77);
						stringBuffer.append(genFeature.getFormattedName());
						stringBuffer.append(this.TEXT_78);
						stringBuffer.append(genFeature.getFeatureKind());
						stringBuffer.append(this.TEXT_79);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(this.TEXT_80);
						if (isGWT) {
							stringBuffer.append(this.TEXT_81);
							stringBuffer
							.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
						}
						stringBuffer.append(this.TEXT_82);
						stringBuffer.append(genFeature.getImportedInternalType(genClass));
						stringBuffer.append(this.TEXT_83);
						stringBuffer.append(genFeature.getSafeName());
						stringBuffer.append(this.TEXT_84);
					}
					if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType()
							&& !genFeature.isMapType()) {
						final String rawListItemType = genFeature.getRawListItemType();
						final int index = rawListItemType.indexOf('[');
						String head = rawListItemType;
						String tail = "";
						if (index != -1) {
							head = rawListItemType.substring(0, index);
							tail = rawListItemType.substring(index);
						}
						stringBuffer.append(this.TEXT_85);
						stringBuffer.append(genFeature.getGetArrayAccessor());
						stringBuffer.append(this.TEXT_86);
						stringBuffer.append(genFeature.getFormattedName());
						stringBuffer.append(this.TEXT_87);
						stringBuffer.append(genFeature.getGetArrayAccessor());
						stringBuffer.append(this.TEXT_88);
						if (genFeature.getQualifiedListItemType(genClass).contains("<")) {
							stringBuffer.append(this.TEXT_89);
						}
						stringBuffer.append(this.TEXT_90);
						stringBuffer.append(rawListItemType);
						stringBuffer.append(this.TEXT_91);
						stringBuffer.append(genFeature.getUpperName());
						stringBuffer.append(this.TEXT_92);
						stringBuffer.append(head);
						stringBuffer.append(this.TEXT_93);
						stringBuffer.append(tail);
						stringBuffer.append(this.TEXT_94);
					}
				} else {
					if (genFeature.hasEDefault() && (!genFeature.isVolatile() || (!genModel.isReflectiveDelegation()
							&& (!genFeature.hasDelegateFeature() || !genFeature.isUnsettable())))) {
						final String staticDefaultValue = genFeature.getStaticDefaultValue();
						stringBuffer.append(this.TEXT_95);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(this.TEXT_96);
						stringBuffer.append(genFeature.getFormattedName());
						stringBuffer.append(this.TEXT_97);
						stringBuffer.append(genFeature.getFeatureKind());
						stringBuffer.append(this.TEXT_98);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(this.TEXT_99);
						if (genModel.useGenerics() && genFeature.isListDataType() && genFeature.isSetDefaultValue()) {
							stringBuffer.append(this.TEXT_100);
						}
						stringBuffer.append(this.TEXT_101);
						stringBuffer.append(genFeature.getImportedType(genClass));
						stringBuffer.append(this.TEXT_102);
						stringBuffer.append(genFeature.getEDefault());
						if ("".equals(staticDefaultValue)) {
							stringBuffer.append(this.TEXT_103);
							stringBuffer.append(genFeature.getEcoreFeature().getDefaultValueLiteral());
							stringBuffer.append(this.TEXT_104);
						} else {
							stringBuffer.append(this.TEXT_105);
							stringBuffer.append(staticDefaultValue);
							stringBuffer.append(this.TEXT_106);
							stringBuffer.append(genModel.getNonNLS(staticDefaultValue));
						}
						stringBuffer.append(this.TEXT_107);
					}
					if (genClass.isField(genFeature)) {
						if (genClass.isFlag(genFeature)) {
							final int flagIndex = genClass.getFlagIndex(genFeature);
							if ((flagIndex > 31) && ((flagIndex % 32) == 0)) {
								stringBuffer.append(this.TEXT_108);
								if (isGWT) {
									stringBuffer.append(this.TEXT_109);
									stringBuffer.append(
											genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
								}
								stringBuffer.append(this.TEXT_110);
								stringBuffer.append(genClass.getFlagsField(genFeature));
								stringBuffer.append(this.TEXT_111);
							}
							if (genFeature.isEnumType()) {
								stringBuffer.append(this.TEXT_112);
								stringBuffer.append(genFeature.getGetAccessor());
								stringBuffer.append(this.TEXT_113);
								stringBuffer.append(genFeature.getFormattedName());
								stringBuffer.append(this.TEXT_114);
								stringBuffer.append(genFeature.getFeatureKind());
								stringBuffer.append(this.TEXT_115);
								stringBuffer.append(genFeature.getUpperName());
								stringBuffer.append(this.TEXT_116);
								stringBuffer.append(flagIndex % 32);
								stringBuffer.append(this.TEXT_117);
								stringBuffer.append(genFeature.getGetAccessor());
								stringBuffer.append(this.TEXT_118);
								stringBuffer.append(genFeature.getFormattedName());
								stringBuffer.append(this.TEXT_119);
								stringBuffer.append(genFeature.getFeatureKind());
								stringBuffer.append(this.TEXT_120);
								stringBuffer.append(genFeature.getUpperName());
								stringBuffer.append(this.TEXT_121);
								if (isJDK50) {
									stringBuffer.append(genFeature.getEDefault());
									stringBuffer.append(this.TEXT_122);
								} else {
									stringBuffer.append(genFeature.getImportedType(genClass));
									stringBuffer.append(this.TEXT_123);
									stringBuffer.append(genFeature.getEDefault());
									stringBuffer.append(this.TEXT_124);
								}
								stringBuffer.append(this.TEXT_125);
								stringBuffer.append(genFeature.getUpperName());
								stringBuffer.append(this.TEXT_126);
								stringBuffer.append(genFeature.getImportedType(genClass));
								stringBuffer.append(this.TEXT_127);
								stringBuffer.append(genFeature.getTypeGenClassifier().getFormattedName());
								stringBuffer.append(this.TEXT_128);
								stringBuffer.append(genFeature.getImportedType(genClass));
								stringBuffer.append(this.TEXT_129);
								stringBuffer.append(genFeature.getUpperName());
								stringBuffer.append(this.TEXT_130);
								if (isJDK50) {
									stringBuffer.append(genFeature.getImportedType(genClass));
									stringBuffer.append(this.TEXT_131);
								} else {
									stringBuffer.append(this.TEXT_132);
									stringBuffer.append(genFeature.getImportedType(genClass));
									stringBuffer.append(this.TEXT_133);
									stringBuffer.append(genFeature.getImportedType(genClass));
									stringBuffer.append(this.TEXT_134);
									stringBuffer.append(genFeature.getImportedType(genClass));
									stringBuffer.append(this.TEXT_135);
									stringBuffer.append(genFeature.getImportedType(genClass));
									stringBuffer.append(this.TEXT_136);
								}
								stringBuffer.append(this.TEXT_137);
							}
							stringBuffer.append(this.TEXT_138);
							stringBuffer.append(genClass.getFlagSize(genFeature) > 1 ? "s" : "");
							stringBuffer.append(this.TEXT_139);
							stringBuffer.append(genFeature.getGetAccessor());
							stringBuffer.append(this.TEXT_140);
							stringBuffer.append(genFeature.getFormattedName());
							stringBuffer.append(this.TEXT_141);
							stringBuffer.append(genFeature.getFeatureKind());
							stringBuffer.append(this.TEXT_142);
							stringBuffer.append(genFeature.getGetAccessor());
							stringBuffer.append(this.TEXT_143);
							stringBuffer.append(genFeature.getUpperName());
							stringBuffer.append(this.TEXT_144);
							stringBuffer.append(genClass.getFlagMask(genFeature));
							stringBuffer.append(this.TEXT_145);
							if (genFeature.isEnumType()) {
								stringBuffer.append(genFeature.getUpperName());
								stringBuffer.append(this.TEXT_146);
							} else {
								stringBuffer.append(flagIndex % 32);
							}
							stringBuffer.append(this.TEXT_147);
						} else {
							stringBuffer.append(this.TEXT_148);
							stringBuffer.append(genFeature.getGetAccessor());
							stringBuffer.append(this.TEXT_149);
							stringBuffer.append(genFeature.getFormattedName());
							stringBuffer.append(this.TEXT_150);
							stringBuffer.append(genFeature.getFeatureKind());
							stringBuffer.append(this.TEXT_151);
							stringBuffer.append(genFeature.getGetAccessor());
							stringBuffer.append(this.TEXT_152);
							if (isGWT) {
								stringBuffer.append(this.TEXT_153);
								stringBuffer.append(
										genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
							}
							stringBuffer.append(this.TEXT_154);
							stringBuffer.append(genFeature.getImportedType(genClass));
							stringBuffer.append(this.TEXT_155);
							stringBuffer.append(genFeature.getSafeName());
							if (genFeature.hasEDefault()) {
								stringBuffer.append(this.TEXT_156);
								stringBuffer.append(genFeature.getEDefault());
							}
							stringBuffer.append(this.TEXT_157);
						}
					}
				}
				if (genClass.isESetField(genFeature)) {
					if (genClass.isESetFlag(genFeature)) {
						final int flagIndex = genClass.getESetFlagIndex(genFeature);
						if ((flagIndex > 31) && ((flagIndex % 32) == 0)) {
							stringBuffer.append(this.TEXT_158);
							if (isGWT) {
								stringBuffer.append(this.TEXT_159);
								stringBuffer.append(
										genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
							}
							stringBuffer.append(this.TEXT_160);
							stringBuffer.append(genClass.getESetFlagsField(genFeature));
							stringBuffer.append(this.TEXT_161);
						}
						stringBuffer.append(this.TEXT_162);
						stringBuffer.append(genFeature.getFormattedName());
						stringBuffer.append(this.TEXT_163);
						stringBuffer.append(genFeature.getFeatureKind());
						stringBuffer.append(this.TEXT_164);
						stringBuffer.append(genFeature.getUpperName());
						stringBuffer.append(this.TEXT_165);
						stringBuffer.append(flagIndex % 32);
						stringBuffer.append(this.TEXT_166);
					} else {
						stringBuffer.append(this.TEXT_167);
						stringBuffer.append(genFeature.getFormattedName());
						stringBuffer.append(this.TEXT_168);
						stringBuffer.append(genFeature.getFeatureKind());
						stringBuffer.append(this.TEXT_169);
						if (isGWT) {
							stringBuffer.append(this.TEXT_170);
							stringBuffer
							.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
						}
						stringBuffer.append(this.TEXT_171);
						stringBuffer.append(genFeature.getUncapName());
						stringBuffer.append(this.TEXT_172);
					}
				}
				// Class/declaredFieldGenFeature.override.javajetinc
			}
		}
		if (isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenFeatures().isEmpty()) {
			stringBuffer.append(this.TEXT_173);
			stringBuffer.append(genClass.getOffsetCorrectionField(null));
			stringBuffer.append(this.TEXT_174);
			stringBuffer.append(genClass.getQualifiedClassifierAccessor());
			stringBuffer.append(this.TEXT_175);
			stringBuffer.append(genClass.getImplementedGenFeatures().get(0).getQualifiedFeatureAccessor());
			stringBuffer.append(this.TEXT_176);
			stringBuffer.append(genClass.getQualifiedFeatureID(genClass.getImplementedGenFeatures().get(0)));
			stringBuffer.append(this.TEXT_177);
		}
		if (isImplementation && !genModel.isReflectiveDelegation()) {
			for (final GenFeature genFeature : genClass.getImplementedGenFeatures()) {
				final GenFeature reverseFeature = genFeature.getReverse();
				if ((reverseFeature != null) && reverseFeature.getGenClass().hasOffsetCorrection()) {
					stringBuffer.append(this.TEXT_178);
					stringBuffer.append(genClass.getOffsetCorrectionField(genFeature));
					stringBuffer.append(this.TEXT_179);
					stringBuffer.append(reverseFeature.getGenClass().getQualifiedClassifierAccessor());
					stringBuffer.append(this.TEXT_180);
					stringBuffer.append(reverseFeature.getQualifiedFeatureAccessor());
					stringBuffer.append(this.TEXT_181);
					stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
					stringBuffer.append(this.TEXT_182);
				}
			}
		}
		if (genModel.isOperationReflection() && isImplementation && genClass.hasOffsetCorrection()
				&& !genClass.getImplementedGenOperations().isEmpty()) {
			stringBuffer.append(this.TEXT_183);
			stringBuffer.append(genClass.getQualifiedClassifierAccessor());
			stringBuffer.append(this.TEXT_184);
			stringBuffer.append(genClass.getImplementedGenOperations().get(0).getQualifiedOperationAccessor());
			stringBuffer.append(this.TEXT_185);
			stringBuffer.append(genClass.getQualifiedOperationID(genClass.getImplementedGenOperations().get(0)));
			stringBuffer.append(this.TEXT_186);
		}
		if (isImplementation) {
			stringBuffer.append(this.TEXT_187);
			if (genModel.isPublicConstructors()) {
				stringBuffer.append(this.TEXT_188);
			} else {
				stringBuffer.append(this.TEXT_189);
			}
			stringBuffer.append(this.TEXT_190);
			stringBuffer.append(genClass.getClassName());
			stringBuffer.append(this.TEXT_191);
			for (final GenFeature genFeature : genClass.getFlagGenFeaturesWithDefault()) {
				stringBuffer.append(this.TEXT_192);
				stringBuffer.append(genClass.getFlagsField(genFeature));
				stringBuffer.append(this.TEXT_193);
				stringBuffer.append(genFeature.getUpperName());
				stringBuffer.append(this.TEXT_194);
				if (!genFeature.isBooleanType()) {
					stringBuffer.append(this.TEXT_195);
				}
				stringBuffer.append(this.TEXT_196);
			}

			stringBuffer.append(this.TEXT_197);
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(this.TEXT_198);
			}
			stringBuffer.append(this.TEXT_199);
			stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
			stringBuffer.append(this.TEXT_200);
			stringBuffer.append(genClass.getQualifiedClassifierAccessor());
			stringBuffer.append(this.TEXT_201);
		}
		if (isImplementation
				&& ((genModel.getFeatureDelegation() == GenDelegationKind.REFLECTIVE_LITERAL)
						|| genModel.isDynamicDelegation())
				&& ((genClass.getClassExtendsGenClass() == null) || ((genClass.getClassExtendsGenClass().getGenModel()
						.getFeatureDelegation() != GenDelegationKind.REFLECTIVE_LITERAL)
						&& !genClass.getClassExtendsGenClass().getGenModel().isDynamicDelegation()))) {
			stringBuffer.append(this.TEXT_202);
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(this.TEXT_203);
			}
			stringBuffer.append(this.TEXT_204);
			stringBuffer.append(genClass.getClassExtendsGenClass() == null ? 0
					: genClass.getClassExtendsGenClass().getAllGenFeatures().size());
			stringBuffer.append(this.TEXT_205);
		}
		// Class/reflectiveDelegation.override.javajetinc
		new Runnable() {
			@Override
			public void run() {
				for (final GenFeature genFeature : (isImplementation ? genClass.getImplementedGenFeatures()
						: genClass.getDeclaredGenFeatures())) {
					if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType()
							&& !genFeature.isMapType()) {
						stringBuffer.append(JavaClassGenerator.this.TEXT_206);
						if (!isImplementation) {
							stringBuffer.append(JavaClassGenerator.this.TEXT_207);
							stringBuffer.append(genFeature.getListItemType(genClass));
							stringBuffer.append(JavaClassGenerator.this.TEXT_208);
							stringBuffer.append(genFeature.getGetArrayAccessor());
							stringBuffer.append(JavaClassGenerator.this.TEXT_209);
						} else {
							stringBuffer.append(JavaClassGenerator.this.TEXT_210);
							stringBuffer.append(genFeature.getListItemType(genClass));
							stringBuffer.append(JavaClassGenerator.this.TEXT_211);
							stringBuffer.append(genFeature.getGetArrayAccessor());
							stringBuffer.append(JavaClassGenerator.this.TEXT_212);
							if (genFeature.isVolatile()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_213);
								stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
								stringBuffer.append(genFeature.getListTemplateArguments(genClass));
								stringBuffer.append(JavaClassGenerator.this.TEXT_214);
								stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
								stringBuffer.append(genFeature.getListTemplateArguments(genClass));
								stringBuffer.append(JavaClassGenerator.this.TEXT_215);
								stringBuffer.append(genFeature.getGetAccessor());
								stringBuffer.append(JavaClassGenerator.this.TEXT_216);
								if (genModel.useGenerics() && !genFeature.getListItemType(genClass).contains("<")
										&& !genFeature.getListItemType(null)
										.equals(genFeature.getListItemType(genClass))) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_217);
									stringBuffer.append(genFeature.getListItemType(genClass));
									stringBuffer.append(JavaClassGenerator.this.TEXT_218);
								}
								stringBuffer.append(genFeature.getUpperName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_219);
							} else {
								stringBuffer.append(JavaClassGenerator.this.TEXT_220);
								stringBuffer.append(genFeature.getSafeName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_221);
								stringBuffer.append(genFeature.getSafeName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_222);
								if (genModel.useGenerics() && !genFeature.getListItemType(genClass).contains("<")
										&& !genFeature.getListItemType(null)
										.equals(genFeature.getListItemType(genClass))) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_223);
									stringBuffer.append(genFeature.getListItemType(genClass));
									stringBuffer.append(JavaClassGenerator.this.TEXT_224);
								}
								stringBuffer.append(genFeature.getUpperName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_225);
								stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
								stringBuffer.append(genFeature.getListTemplateArguments(genClass));
								stringBuffer.append(JavaClassGenerator.this.TEXT_226);
								stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
								stringBuffer.append(genFeature.getListTemplateArguments(genClass));
								stringBuffer.append(JavaClassGenerator.this.TEXT_227);
								stringBuffer.append(genFeature.getSafeName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_228);
							}
							stringBuffer.append(JavaClassGenerator.this.TEXT_229);
							stringBuffer.append(genFeature.getListItemType(genClass));
							stringBuffer.append(JavaClassGenerator.this.TEXT_230);
						}
						stringBuffer.append(JavaClassGenerator.this.TEXT_231);
						if (!isImplementation) {
							stringBuffer.append(JavaClassGenerator.this.TEXT_232);
							stringBuffer.append(genFeature.getListItemType(genClass));
							stringBuffer.append(JavaClassGenerator.this.TEXT_233);
							stringBuffer.append(genFeature.getAccessorName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_234);
						} else {
							stringBuffer.append(JavaClassGenerator.this.TEXT_235);
							stringBuffer.append(genFeature.getListItemType(genClass));
							stringBuffer.append(JavaClassGenerator.this.TEXT_236);
							stringBuffer.append(genFeature.getAccessorName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_237);
							if (!genModel.useGenerics()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_238);
								stringBuffer.append(genFeature.getListItemType(genClass));
								stringBuffer.append(JavaClassGenerator.this.TEXT_239);
							}
							stringBuffer.append(genFeature.getGetAccessor());
							stringBuffer.append(JavaClassGenerator.this.TEXT_240);
						}
						stringBuffer.append(JavaClassGenerator.this.TEXT_241);
						if (!isImplementation) {
							stringBuffer.append(JavaClassGenerator.this.TEXT_242);
							stringBuffer.append(genFeature.getAccessorName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_243);
						} else {
							stringBuffer.append(JavaClassGenerator.this.TEXT_244);
							stringBuffer.append(genFeature.getAccessorName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_245);
							if (genFeature.isVolatile()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_246);
								stringBuffer.append(genFeature.getGetAccessor());
								stringBuffer.append(JavaClassGenerator.this.TEXT_247);
							} else {
								stringBuffer.append(JavaClassGenerator.this.TEXT_248);
								stringBuffer.append(genFeature.getSafeName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_249);
								stringBuffer.append(genFeature.getSafeName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_250);
							}
							stringBuffer.append(JavaClassGenerator.this.TEXT_251);
						}
						stringBuffer.append(JavaClassGenerator.this.TEXT_252);
						if (!isImplementation) {
							stringBuffer.append(JavaClassGenerator.this.TEXT_253);
							stringBuffer.append(genFeature.getAccessorName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_254);
							stringBuffer.append(genFeature.getListItemType(genClass));
							stringBuffer.append(JavaClassGenerator.this.TEXT_255);
							stringBuffer.append(genFeature.getCapName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_256);
						} else {
							stringBuffer.append(JavaClassGenerator.this.TEXT_257);
							stringBuffer.append(genFeature.getAccessorName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_258);
							stringBuffer.append(genFeature.getListItemType(genClass));
							stringBuffer.append(JavaClassGenerator.this.TEXT_259);
							stringBuffer.append(genFeature.getCapName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_260);
							stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
							stringBuffer.append(genFeature.getListTemplateArguments(genClass));
							stringBuffer.append(JavaClassGenerator.this.TEXT_261);
							stringBuffer.append(genFeature.getGetAccessor());
							stringBuffer.append(JavaClassGenerator.this.TEXT_262);
							stringBuffer.append(genFeature.getCapName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_263);
							stringBuffer.append(genFeature.getCapName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_264);
						}
						stringBuffer.append(JavaClassGenerator.this.TEXT_265);
						if (!isImplementation) {
							stringBuffer.append(JavaClassGenerator.this.TEXT_266);
							stringBuffer.append(genFeature.getAccessorName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_267);
							stringBuffer.append(genFeature.getListItemType(genClass));
							stringBuffer.append(JavaClassGenerator.this.TEXT_268);
						} else {
							stringBuffer.append(JavaClassGenerator.this.TEXT_269);
							stringBuffer.append(genFeature.getAccessorName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_270);
							stringBuffer.append(genFeature.getListItemType(genClass));
							stringBuffer.append(JavaClassGenerator.this.TEXT_271);
							stringBuffer.append(genFeature.getGetAccessor());
							stringBuffer.append(JavaClassGenerator.this.TEXT_272);
						}
					}
					if (genFeature.isGet() && (isImplementation || !genFeature.isSuppressedGetVisibility())) {
						if (isInterface) {
							stringBuffer.append(JavaClassGenerator.this.TEXT_273);
							stringBuffer.append(genFeature.getFormattedName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_274);
							stringBuffer.append(genFeature.getFeatureKind());
							stringBuffer.append(JavaClassGenerator.this.TEXT_275);
							if (genFeature.isListType()) {
								if (genFeature.isMapType()) {
									final GenFeature keyFeature = genFeature.getMapEntryTypeGenClass()
											.getMapEntryKeyFeature();
									final GenFeature valueFeature = genFeature.getMapEntryTypeGenClass()
											.getMapEntryValueFeature();
									stringBuffer.append(JavaClassGenerator.this.TEXT_276);
									if (keyFeature.isListType()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_277);
										stringBuffer.append(keyFeature.getQualifiedListItemType(genClass));
										stringBuffer.append(JavaClassGenerator.this.TEXT_278);
									} else {
										stringBuffer.append(JavaClassGenerator.this.TEXT_279);
										stringBuffer.append(keyFeature.getType(genClass));
										stringBuffer.append(JavaClassGenerator.this.TEXT_280);
									}
									stringBuffer.append(JavaClassGenerator.this.TEXT_281);
									if (valueFeature.isListType()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_282);
										stringBuffer.append(valueFeature.getQualifiedListItemType(genClass));
										stringBuffer.append(JavaClassGenerator.this.TEXT_283);
									} else {
										stringBuffer.append(JavaClassGenerator.this.TEXT_284);
										stringBuffer.append(valueFeature.getType(genClass));
										stringBuffer.append(JavaClassGenerator.this.TEXT_285);
									}
									stringBuffer.append(JavaClassGenerator.this.TEXT_286);
								} else if (!genFeature.isWrappedFeatureMapType()
										&& (!genModel.isSuppressEMFMetaData() || !"org.eclipse.emf.ecore.EObject"
												.equals(genFeature.getQualifiedListItemType(genClass)))) {
									final String typeName = genFeature.getQualifiedListItemType(genClass);
									String head = typeName;
									String tail = "";
									int index = typeName.indexOf('<');
									if (index == -1) {
										index = typeName.indexOf('[');
									}
									if (index != -1) {
										head = typeName.substring(0, index);
										tail = typeName.substring(index).replaceAll("<", "&lt;");
									}

									stringBuffer.append(JavaClassGenerator.this.TEXT_287);
									stringBuffer.append(head);
									stringBuffer.append(JavaClassGenerator.this.TEXT_288);
									stringBuffer.append(tail);
									stringBuffer.append(JavaClassGenerator.this.TEXT_289);
								}
							} else if (genFeature.isSetDefaultValue()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_290);
								stringBuffer.append(genFeature.getDefaultValue());
								stringBuffer.append(JavaClassGenerator.this.TEXT_291);
							}
							if (genFeature.getTypeGenEnum() != null) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_292);
								stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_293);
							}
							if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) {
								final GenFeature reverseGenFeature = genFeature.getReverse();
								if (!reverseGenFeature.isSuppressedGetVisibility()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_294);
									stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_295);
									stringBuffer.append(reverseGenFeature.getGetAccessor());
									stringBuffer.append(JavaClassGenerator.this.TEXT_296);
									stringBuffer.append(reverseGenFeature.getFormattedName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_297);
								}
							}
							stringBuffer.append(JavaClassGenerator.this.TEXT_298);
							if (!genFeature.hasDocumentation()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_299);
								stringBuffer.append(genFeature.getFormattedName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_300);
								stringBuffer.append(genFeature.getFeatureKind());
								stringBuffer.append(JavaClassGenerator.this.TEXT_301);
							}
							stringBuffer.append(JavaClassGenerator.this.TEXT_302);
							if (genFeature.hasDocumentation()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_303);
								stringBuffer.append(genFeature.getDocumentation(genModel.getIndentation(stringBuffer)));
								stringBuffer.append(JavaClassGenerator.this.TEXT_304);
							}
							stringBuffer.append(JavaClassGenerator.this.TEXT_305);
							stringBuffer.append(genFeature.getFormattedName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_306);
							stringBuffer.append(genFeature.getFeatureKind());
							stringBuffer.append(JavaClassGenerator.this.TEXT_307);
							if (genFeature.getTypeGenEnum() != null) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_308);
								stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
							}
							if (genFeature.isUnsettable()) {
								if (!genFeature.isSuppressedIsSetVisibility()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_309);
									stringBuffer.append(genFeature.getAccessorName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_310);
								}
								if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_311);
									stringBuffer.append(genFeature.getAccessorName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_312);
								}
							}
							if (genFeature.isChangeable() && !genFeature.isListType()
									&& !genFeature.isSuppressedSetVisibility()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_313);
								stringBuffer.append(genFeature.getAccessorName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_314);
								stringBuffer.append(genFeature.getRawImportedBoundType());
								stringBuffer.append(JavaClassGenerator.this.TEXT_315);
							}
							if (!genModel.isSuppressEMFMetaData()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_316);
								stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_317);
								stringBuffer.append(genFeature.getFeatureAccessorName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_318);
							}
							if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) {
								final GenFeature reverseGenFeature = genFeature.getReverse();
								if (!reverseGenFeature.isSuppressedGetVisibility()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_319);
									stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_320);
									stringBuffer.append(reverseGenFeature.getGetAccessor());
								}
							}
							if (!genModel.isSuppressEMFModelTags()) {
								boolean first = true;
								for (final StringTokenizer stringTokenizer = new StringTokenizer(genFeature.getModelInfo(),
										"\n\r"); stringTokenizer.hasMoreTokens();) {
									final String modelInfo = stringTokenizer.nextToken();
									if (first) {
										first = false;
										stringBuffer.append(JavaClassGenerator.this.TEXT_321);
										stringBuffer.append(modelInfo);
									} else {
										stringBuffer.append(JavaClassGenerator.this.TEXT_322);
										stringBuffer.append(modelInfo);
									}
								}
								if (first) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_323);
								}
							}
							stringBuffer.append(JavaClassGenerator.this.TEXT_324);
							// Class/getGenFeature.javadoc.override.javajetinc
						} else {
							stringBuffer.append(JavaClassGenerator.this.TEXT_325);
							if (isJDK50) { // Class/getGenFeature.annotations.insert.javajetinc
							}
						}
						if (!isImplementation) {
							stringBuffer.append(JavaClassGenerator.this.TEXT_326);
							stringBuffer.append(genFeature.getImportedType(genClass));
							stringBuffer.append(JavaClassGenerator.this.TEXT_327);
							stringBuffer.append(genFeature.getGetAccessor());
							stringBuffer.append(JavaClassGenerator.this.TEXT_328);
						} else {
							if (genModel.useGenerics() && (((genFeature.isContainer() || genFeature.isResolveProxies())
									&& !genFeature.isListType()
									&& (!genModel.isReflectiveDelegation() || !genModel.isDynamicDelegation())
									&& genFeature.isUncheckedCast(genClass))
									|| (genFeature.isListType() && !genFeature.isFeatureMapType()
											&& (genModel.isReflectiveDelegation() || genModel.isVirtualDelegation()
													|| genModel.isDynamicDelegation()))
									|| (genFeature.isListDataType() && genFeature.hasDelegateFeature())
									|| (genFeature.isListType() && genFeature.hasSettingDelegate()))) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_329);
							}
							stringBuffer.append(JavaClassGenerator.this.TEXT_330);
							stringBuffer.append(genFeature.getImportedType(genClass));
							stringBuffer.append(JavaClassGenerator.this.TEXT_331);
							stringBuffer.append(genFeature.getGetAccessor());
							if (genClass.hasCollidingGetAccessorOperation(genFeature)) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_332);
							}
							stringBuffer.append(JavaClassGenerator.this.TEXT_333);

							if (genModel.isDynamicDelegation()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_334);
								if (!isJDK50 && genFeature.isPrimitiveType()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_335);
								}
								stringBuffer.append(JavaClassGenerator.this.TEXT_336);
								stringBuffer.append(genFeature.getObjectType(genClass));
								stringBuffer.append(JavaClassGenerator.this.TEXT_337);
								stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
								stringBuffer.append(JavaClassGenerator.this.TEXT_338);
								stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
								stringBuffer.append(JavaClassGenerator.this.TEXT_339);
								stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
								stringBuffer.append(JavaClassGenerator.this.TEXT_340);
								if (!isJDK50 && genFeature.isPrimitiveType()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_341);
									stringBuffer.append(genFeature.getPrimitiveValueFunction());
									stringBuffer.append(JavaClassGenerator.this.TEXT_342);
								}
								stringBuffer.append(JavaClassGenerator.this.TEXT_343);
							} else if (genModel.isReflectiveDelegation()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_344);
								if (!isJDK50 && genFeature.isPrimitiveType()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_345);
								}
								stringBuffer.append(JavaClassGenerator.this.TEXT_346);
								stringBuffer.append(genFeature.getObjectType(genClass));
								stringBuffer.append(JavaClassGenerator.this.TEXT_347);
								stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
								stringBuffer.append(JavaClassGenerator.this.TEXT_348);
								if (!isJDK50 && genFeature.isPrimitiveType()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_349);
									stringBuffer.append(genFeature.getPrimitiveValueFunction());
									stringBuffer.append(JavaClassGenerator.this.TEXT_350);
								}
								stringBuffer.append(JavaClassGenerator.this.TEXT_351);
							} else if (genFeature.hasSettingDelegate()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_352);
								if (!isJDK50 && genFeature.isPrimitiveType()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_353);
								}
								stringBuffer.append(JavaClassGenerator.this.TEXT_354);
								stringBuffer.append(genFeature.getObjectType(genClass));
								stringBuffer.append(JavaClassGenerator.this.TEXT_355);
								stringBuffer.append(genFeature.getUpperName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_356);
								if (!isJDK50 && genFeature.isPrimitiveType()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_357);
									stringBuffer.append(genFeature.getPrimitiveValueFunction());
									stringBuffer.append(JavaClassGenerator.this.TEXT_358);
								}
								stringBuffer.append(JavaClassGenerator.this.TEXT_359);
							} else if (!genFeature.isVolatile()) {
								if (genFeature.isListType()) {
									if (genModel.isVirtualDelegation()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_360);
										stringBuffer.append(genFeature.getImportedType(genClass));
										stringBuffer.append(JavaClassGenerator.this.TEXT_361);
										stringBuffer.append(genFeature.getSafeName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_362);
										stringBuffer.append(genFeature.getImportedType(genClass));
										stringBuffer.append(JavaClassGenerator.this.TEXT_363);
										stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
										stringBuffer.append(positiveOffsetCorrection);
										stringBuffer.append(JavaClassGenerator.this.TEXT_364);
									}
									stringBuffer.append(JavaClassGenerator.this.TEXT_365);
									stringBuffer.append(genFeature.getSafeName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_366);
									if (genModel.isVirtualDelegation()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_367);
										stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
										stringBuffer.append(positiveOffsetCorrection);
										stringBuffer.append(JavaClassGenerator.this.TEXT_368);
										stringBuffer.append(genFeature.getSafeName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_369);
										stringBuffer.append(genClass.getListConstructor(genFeature));
										stringBuffer.append(JavaClassGenerator.this.TEXT_370);
									} else {
										stringBuffer.append(JavaClassGenerator.this.TEXT_371);
										stringBuffer.append(genFeature.getSafeName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_372);
										stringBuffer.append(genClass.getListConstructor(genFeature));
										stringBuffer.append(JavaClassGenerator.this.TEXT_373);
									}
									stringBuffer.append(JavaClassGenerator.this.TEXT_374);
									stringBuffer.append(genFeature.getSafeName());
									stringBuffer
									.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()
											? ".map()"
													: "");
									stringBuffer.append(JavaClassGenerator.this.TEXT_375);
								} else if (genFeature.isContainer()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_376);
									stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
									stringBuffer.append(positiveOffsetCorrection);
									stringBuffer.append(JavaClassGenerator.this.TEXT_377);
									stringBuffer.append(genFeature.getImportedType(genClass));
									stringBuffer.append(JavaClassGenerator.this.TEXT_378);
								} else {
									if (genFeature.isResolveProxies()) {
										if (genModel.isVirtualDelegation()) {
											stringBuffer.append(JavaClassGenerator.this.TEXT_379);
											stringBuffer.append(genFeature.getImportedType(genClass));
											stringBuffer.append(JavaClassGenerator.this.TEXT_380);
											stringBuffer.append(genFeature.getSafeName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_381);
											stringBuffer.append(genFeature.getImportedType(genClass));
											stringBuffer.append(JavaClassGenerator.this.TEXT_382);
											stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
											stringBuffer.append(positiveOffsetCorrection);
											if (genFeature.hasEDefault()) {
												stringBuffer.append(JavaClassGenerator.this.TEXT_383);
												stringBuffer.append(genFeature.getEDefault());
											}
											stringBuffer.append(JavaClassGenerator.this.TEXT_384);
										}
										stringBuffer.append(JavaClassGenerator.this.TEXT_385);
										stringBuffer.append(genFeature.getSafeName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_386);
										stringBuffer.append(genFeature.getSafeNameAsEObject());
										stringBuffer.append(JavaClassGenerator.this.TEXT_387);
										stringBuffer.append(
												genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
										stringBuffer.append(JavaClassGenerator.this.TEXT_388);
										stringBuffer.append(genFeature.getCapName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_389);
										stringBuffer.append(
												genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
										stringBuffer.append(JavaClassGenerator.this.TEXT_390);
										stringBuffer.append(genFeature.getSafeName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_391);
										stringBuffer.append(genFeature.getSafeName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_392);
										stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
										stringBuffer.append(JavaClassGenerator.this.TEXT_393);
										stringBuffer.append(genFeature.getCapName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_394);
										stringBuffer.append(genFeature.getSafeName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_395);
										stringBuffer.append(genFeature.getCapName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_396);
										if (genFeature.isEffectiveContains()) {
											stringBuffer.append(JavaClassGenerator.this.TEXT_397);
											stringBuffer.append(
													genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
											stringBuffer.append(JavaClassGenerator.this.TEXT_398);
											stringBuffer.append(genFeature.getCapName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_399);
											stringBuffer.append(
													genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
											stringBuffer.append(JavaClassGenerator.this.TEXT_400);
											stringBuffer.append(genFeature.getSafeName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_401);
											if (!genFeature.isBidirectional()) {
												stringBuffer.append(JavaClassGenerator.this.TEXT_402);
												stringBuffer.append(genModel.getImportedName(
														"org.eclipse.emf.common.notify.NotificationChain"));
												stringBuffer.append(JavaClassGenerator.this.TEXT_403);
												stringBuffer.append(genFeature.getCapName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_404);
												stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
												stringBuffer.append(negativeOffsetCorrection);
												stringBuffer.append(JavaClassGenerator.this.TEXT_405);
											} else {
												final GenFeature reverseFeature = genFeature.getReverse();
												final GenClass targetClass = reverseFeature.getGenClass();
												final String reverseOffsetCorrection = targetClass.hasOffsetCorrection()
														? " + " + genClass.getOffsetCorrectionField(genFeature)
														: "";
												stringBuffer.append(JavaClassGenerator.this.TEXT_406);
												stringBuffer.append(genModel.getImportedName(
														"org.eclipse.emf.common.notify.NotificationChain"));
												stringBuffer.append(JavaClassGenerator.this.TEXT_407);
												stringBuffer.append(genFeature.getCapName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_408);
												stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
												stringBuffer.append(reverseOffsetCorrection);
												stringBuffer.append(JavaClassGenerator.this.TEXT_409);
												stringBuffer.append(targetClass.getRawImportedInterfaceName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_410);
											}
											stringBuffer.append(JavaClassGenerator.this.TEXT_411);
											stringBuffer.append(genFeature.getCapName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_412);
											if (!genFeature.isBidirectional()) {
												stringBuffer.append(JavaClassGenerator.this.TEXT_413);
												stringBuffer.append(genFeature.getCapName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_414);
												stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
												stringBuffer.append(negativeOffsetCorrection);
												stringBuffer.append(JavaClassGenerator.this.TEXT_415);
											} else {
												final GenFeature reverseFeature = genFeature.getReverse();
												final GenClass targetClass = reverseFeature.getGenClass();
												final String reverseOffsetCorrection = targetClass.hasOffsetCorrection()
														? " + " + genClass.getOffsetCorrectionField(genFeature)
														: "";
												stringBuffer.append(JavaClassGenerator.this.TEXT_416);
												stringBuffer.append(genFeature.getCapName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_417);
												stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
												stringBuffer.append(reverseOffsetCorrection);
												stringBuffer.append(JavaClassGenerator.this.TEXT_418);
												stringBuffer.append(targetClass.getRawImportedInterfaceName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_419);
											}
											stringBuffer.append(JavaClassGenerator.this.TEXT_420);
										} else if (genModel.isVirtualDelegation()) {
											stringBuffer.append(JavaClassGenerator.this.TEXT_421);
											stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
											stringBuffer.append(positiveOffsetCorrection);
											stringBuffer.append(JavaClassGenerator.this.TEXT_422);
											stringBuffer.append(genFeature.getSafeName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_423);
										}
										if (!genModel.isSuppressNotification()) {
											stringBuffer.append(JavaClassGenerator.this.TEXT_424);
											stringBuffer.append(genModel
													.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
											stringBuffer.append(JavaClassGenerator.this.TEXT_425);
											stringBuffer.append(genModel
													.getImportedName("org.eclipse.emf.common.notify.Notification"));
											stringBuffer.append(JavaClassGenerator.this.TEXT_426);
											stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
											stringBuffer.append(positiveOffsetCorrection);
											stringBuffer.append(JavaClassGenerator.this.TEXT_427);
											stringBuffer.append(genFeature.getCapName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_428);
											stringBuffer.append(genFeature.getSafeName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_429);
										}
										stringBuffer.append(JavaClassGenerator.this.TEXT_430);
									}
									if (!genFeature.isResolveProxies() && genModel.isVirtualDelegation()
											&& !genFeature.isPrimitiveType()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_431);
										stringBuffer.append(genFeature.getImportedType(genClass));
										stringBuffer.append(JavaClassGenerator.this.TEXT_432);
										stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
										stringBuffer.append(positiveOffsetCorrection);
										if (genFeature.hasEDefault()) {
											stringBuffer.append(JavaClassGenerator.this.TEXT_433);
											stringBuffer.append(genFeature.getEDefault());
										}
										stringBuffer.append(JavaClassGenerator.this.TEXT_434);
									} else if (genClass.isFlag(genFeature)) {
										if (genFeature.isBooleanType()) {
											stringBuffer.append(JavaClassGenerator.this.TEXT_435);
											stringBuffer.append(genClass.getFlagsField(genFeature));
											stringBuffer.append(JavaClassGenerator.this.TEXT_436);
											stringBuffer.append(genFeature.getUpperName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_437);
										} else {
											stringBuffer.append(JavaClassGenerator.this.TEXT_438);
											stringBuffer.append(genFeature.getUpperName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_439);
											stringBuffer.append(genClass.getFlagsField(genFeature));
											stringBuffer.append(JavaClassGenerator.this.TEXT_440);
											stringBuffer.append(genFeature.getUpperName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_441);
											stringBuffer.append(genFeature.getUpperName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_442);
										}
									} else {
										stringBuffer.append(JavaClassGenerator.this.TEXT_443);
										stringBuffer.append(genFeature.getSafeName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_444);
									}
								}
							} else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_445);
								stringBuffer.append(genFeature.getImportedType(genClass));
								stringBuffer.append(JavaClassGenerator.this.TEXT_446);
								stringBuffer.append(genFeature.getSafeName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_447);
								stringBuffer.append(genFeature.getAccessorName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_448);
								stringBuffer.append(genFeature.getSafeName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_449);
								stringBuffer.append(genFeature.getSafeNameAsEObject());
								stringBuffer.append(JavaClassGenerator.this.TEXT_450);
								stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
								stringBuffer.append(JavaClassGenerator.this.TEXT_451);
								stringBuffer
								.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
								stringBuffer.append(JavaClassGenerator.this.TEXT_452);
								stringBuffer.append(genFeature.getSafeName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_453);
								stringBuffer.append(genFeature.getSafeName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_454);
							} else if (genFeature.hasDelegateFeature()) {
								final GenFeature delegateFeature = genFeature.getDelegateFeature();
								if (genFeature.isFeatureMapType()) {
									final String featureMapEntryTemplateArgument = isJDK50 ? "<"
											+ genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap")
											+ ".Entry>" : "";
									if (delegateFeature.isWrappedFeatureMapType()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_455);
										stringBuffer
										.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
										stringBuffer.append(JavaClassGenerator.this.TEXT_456);
										stringBuffer.append(
												genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
										stringBuffer.append(JavaClassGenerator.this.TEXT_457);
										stringBuffer.append(
												genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
										stringBuffer.append(JavaClassGenerator.this.TEXT_458);
										stringBuffer.append(delegateFeature.getAccessorName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_459);
										stringBuffer.append(featureMapEntryTemplateArgument);
										stringBuffer.append(JavaClassGenerator.this.TEXT_460);
										stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
										stringBuffer.append(JavaClassGenerator.this.TEXT_461);
									} else {
										stringBuffer.append(JavaClassGenerator.this.TEXT_462);
										stringBuffer.append(
												genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
										stringBuffer.append(JavaClassGenerator.this.TEXT_463);
										stringBuffer.append(delegateFeature.getAccessorName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_464);
										stringBuffer.append(featureMapEntryTemplateArgument);
										stringBuffer.append(JavaClassGenerator.this.TEXT_465);
										stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
										stringBuffer.append(JavaClassGenerator.this.TEXT_466);
									}
								} else if (genFeature.isListType()) {
									if (delegateFeature.isWrappedFeatureMapType()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_467);
										stringBuffer.append(
												genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
										stringBuffer.append(JavaClassGenerator.this.TEXT_468);
										stringBuffer.append(delegateFeature.getAccessorName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_469);
										stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
										stringBuffer.append(JavaClassGenerator.this.TEXT_470);
									} else {
										stringBuffer.append(JavaClassGenerator.this.TEXT_471);
										stringBuffer.append(delegateFeature.getAccessorName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_472);
										stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
										stringBuffer.append(JavaClassGenerator.this.TEXT_473);
									}
								} else if (delegateFeature.isWrappedFeatureMapType()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_474);
									if (!isJDK50 && genFeature.isPrimitiveType()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_475);
									}
									if ((genFeature.getTypeGenDataType() == null)
											|| !genFeature.getTypeGenDataType().isObjectType()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_476);
										stringBuffer.append(genFeature.getObjectType(genClass));
										stringBuffer.append(JavaClassGenerator.this.TEXT_477);
									}
									stringBuffer.append(JavaClassGenerator.this.TEXT_478);
									stringBuffer.append(
											genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
									stringBuffer.append(JavaClassGenerator.this.TEXT_479);
									stringBuffer.append(delegateFeature.getAccessorName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_480);
									stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
									stringBuffer.append(JavaClassGenerator.this.TEXT_481);
									if (!isJDK50 && genFeature.isPrimitiveType()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_482);
										stringBuffer.append(genFeature.getPrimitiveValueFunction());
										stringBuffer.append(JavaClassGenerator.this.TEXT_483);
									}
									stringBuffer.append(JavaClassGenerator.this.TEXT_484);
								} else {
									stringBuffer.append(JavaClassGenerator.this.TEXT_485);
									if (!isJDK50 && genFeature.isPrimitiveType()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_486);
									}
									if ((genFeature.getTypeGenDataType() == null)
											|| !genFeature.getTypeGenDataType().isObjectType()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_487);
										stringBuffer.append(genFeature.getObjectType(genClass));
										stringBuffer.append(JavaClassGenerator.this.TEXT_488);
									}
									stringBuffer.append(JavaClassGenerator.this.TEXT_489);
									stringBuffer.append(delegateFeature.getAccessorName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_490);
									stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
									stringBuffer.append(JavaClassGenerator.this.TEXT_491);
									if (!isJDK50 && genFeature.isPrimitiveType()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_492);
										stringBuffer.append(genFeature.getPrimitiveValueFunction());
										stringBuffer.append(JavaClassGenerator.this.TEXT_493);
									}
									stringBuffer.append(JavaClassGenerator.this.TEXT_494);
								}
							} else if (genClass.getGetAccessorOperation(genFeature) != null) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_495);
								stringBuffer.append(genClass.getGetAccessorOperation(genFeature)
										.getBody(genModel.getIndentation(stringBuffer)));
							} else if (genFeature.hasGetterBody()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_496);
								stringBuffer
								.append(genFeature.getGetterBody(genModel.getIndentation(stringBuffer)));
							} else {
								stringBuffer.append(JavaClassGenerator.this.TEXT_497);
								stringBuffer.append(genFeature.getFormattedName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_498);
								stringBuffer.append(genFeature.getFeatureKind());
								stringBuffer.append(JavaClassGenerator.this.TEXT_499);
								if (genFeature.isListType()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_500);
									if (genFeature.isMapType()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_501);
									} else if (genFeature.isFeatureMapType()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_502);
									} else {
										stringBuffer.append(JavaClassGenerator.this.TEXT_503);
									}
									stringBuffer.append(JavaClassGenerator.this.TEXT_504);
								}
								stringBuffer.append(JavaClassGenerator.this.TEXT_505);
								// Class/getGenFeature.todo.override.javajetinc
							}
							stringBuffer.append(JavaClassGenerator.this.TEXT_506);
						}
						// Class/getGenFeature.override.javajetinc
					}
					if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
						stringBuffer.append(JavaClassGenerator.this.TEXT_507);
						if (isJDK50) { // Class/basicGetGenFeature.annotations.insert.javajetinc
						}
						stringBuffer.append(JavaClassGenerator.this.TEXT_508);
						stringBuffer.append(genFeature.getImportedType(genClass));
						stringBuffer.append(JavaClassGenerator.this.TEXT_509);
						stringBuffer.append(genFeature.getAccessorName());
						stringBuffer.append(JavaClassGenerator.this.TEXT_510);
						if (genModel.isDynamicDelegation()) {
							stringBuffer.append(JavaClassGenerator.this.TEXT_511);
							stringBuffer.append(genFeature.getImportedType(genClass));
							stringBuffer.append(JavaClassGenerator.this.TEXT_512);
							stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
							stringBuffer.append(JavaClassGenerator.this.TEXT_513);
							stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
							stringBuffer.append(JavaClassGenerator.this.TEXT_514);
							stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
							stringBuffer.append(JavaClassGenerator.this.TEXT_515);
						} else if (genFeature.hasSettingDelegate()) {
							stringBuffer.append(JavaClassGenerator.this.TEXT_516);
							if (!isJDK50 && genFeature.isPrimitiveType()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_517);
							}
							stringBuffer.append(JavaClassGenerator.this.TEXT_518);
							stringBuffer.append(genFeature.getObjectType(genClass));
							stringBuffer.append(JavaClassGenerator.this.TEXT_519);
							stringBuffer.append(genFeature.getUpperName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_520);
							if (!isJDK50 && genFeature.isPrimitiveType()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_521);
								stringBuffer.append(genFeature.getPrimitiveValueFunction());
								stringBuffer.append(JavaClassGenerator.this.TEXT_522);
							}
							stringBuffer.append(JavaClassGenerator.this.TEXT_523);
						} else if (genFeature.isContainer()) {
							stringBuffer.append(JavaClassGenerator.this.TEXT_524);
							stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
							stringBuffer.append(positiveOffsetCorrection);
							stringBuffer.append(JavaClassGenerator.this.TEXT_525);
							stringBuffer.append(genFeature.getImportedType(genClass));
							stringBuffer.append(JavaClassGenerator.this.TEXT_526);
						} else if (!genFeature.isVolatile()) {
							if (genModel.isVirtualDelegation()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_527);
								stringBuffer.append(genFeature.getImportedType(genClass));
								stringBuffer.append(JavaClassGenerator.this.TEXT_528);
								stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
								stringBuffer.append(positiveOffsetCorrection);
								stringBuffer.append(JavaClassGenerator.this.TEXT_529);
							} else {
								stringBuffer.append(JavaClassGenerator.this.TEXT_530);
								stringBuffer.append(genFeature.getSafeName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_531);
							}
						} else if (genFeature.hasDelegateFeature()) {
							final GenFeature delegateFeature = genFeature.getDelegateFeature();
							if (delegateFeature.isWrappedFeatureMapType()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_532);
								stringBuffer.append(genFeature.getImportedType(genClass));
								stringBuffer.append(JavaClassGenerator.this.TEXT_533);
								stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
								stringBuffer.append(JavaClassGenerator.this.TEXT_534);
								stringBuffer.append(delegateFeature.getAccessorName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_535);
								stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
								stringBuffer.append(JavaClassGenerator.this.TEXT_536);
							} else {
								stringBuffer.append(JavaClassGenerator.this.TEXT_537);
								stringBuffer.append(genFeature.getImportedType(genClass));
								stringBuffer.append(JavaClassGenerator.this.TEXT_538);
								stringBuffer.append(delegateFeature.getAccessorName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_539);
								stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
								stringBuffer.append(JavaClassGenerator.this.TEXT_540);
							}
						} else {
							stringBuffer.append(JavaClassGenerator.this.TEXT_541);
							stringBuffer.append(genFeature.getFormattedName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_542);
							stringBuffer.append(genFeature.getFeatureKind());
							stringBuffer.append(JavaClassGenerator.this.TEXT_543);
							// Class/basicGetGenFeature.todo.override.javajetinc
						}
						stringBuffer.append(JavaClassGenerator.this.TEXT_544);
						// Class/basicGetGenFeature.override.javajetinc
					}
					if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
						stringBuffer.append(JavaClassGenerator.this.TEXT_545);
						if (isJDK50) { // Class/basicSetGenFeature.annotations.insert.javajetinc
						}
						stringBuffer.append(JavaClassGenerator.this.TEXT_546);
						stringBuffer
						.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
						stringBuffer.append(JavaClassGenerator.this.TEXT_547);
						stringBuffer.append(genFeature.getAccessorName());
						stringBuffer.append(JavaClassGenerator.this.TEXT_548);
						stringBuffer.append(genFeature.getImportedInternalType(genClass));
						stringBuffer.append(JavaClassGenerator.this.TEXT_549);
						stringBuffer.append(genFeature.getCapName());
						stringBuffer.append(JavaClassGenerator.this.TEXT_550);
						stringBuffer
						.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
						stringBuffer.append(JavaClassGenerator.this.TEXT_551);
						if (genFeature.isContainer()) {
							stringBuffer.append(JavaClassGenerator.this.TEXT_552);
							stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
							stringBuffer.append(JavaClassGenerator.this.TEXT_553);
							stringBuffer.append(genFeature.getCapName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_554);
							stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
							stringBuffer.append(positiveOffsetCorrection);
							stringBuffer.append(JavaClassGenerator.this.TEXT_555);
							stringBuffer.append(JavaClassGenerator.this.TEXT_556);
						} else if (genModel.isDynamicDelegation()) {
							stringBuffer.append(JavaClassGenerator.this.TEXT_557);
							stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
							stringBuffer.append(JavaClassGenerator.this.TEXT_558);
							stringBuffer.append(genFeature.getCapName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_559);
							stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
							stringBuffer.append(positiveOffsetCorrection);
							stringBuffer.append(JavaClassGenerator.this.TEXT_560);
							stringBuffer.append(JavaClassGenerator.this.TEXT_561);
						} else if (!genFeature.isVolatile()) {
							if (genModel.isVirtualDelegation()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_562);
								stringBuffer.append(genFeature.getCapName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_563);
								stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
								stringBuffer.append(positiveOffsetCorrection);
								stringBuffer.append(JavaClassGenerator.this.TEXT_564);
								stringBuffer.append(genFeature.getCapName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_565);
							} else {
								stringBuffer.append(JavaClassGenerator.this.TEXT_566);
								stringBuffer.append(genFeature.getImportedType(genClass));
								stringBuffer.append(JavaClassGenerator.this.TEXT_567);
								stringBuffer.append(genFeature.getCapName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_568);
								stringBuffer.append(genFeature.getSafeName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_569);
								stringBuffer.append(genFeature.getSafeName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_570);
								stringBuffer.append(genFeature.getCapName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_571);
							}
							if (genFeature.isUnsettable()) {
								if (genModel.isVirtualDelegation()) {
									if (!genModel.isSuppressNotification()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_572);
										stringBuffer.append(genFeature.getCapName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_573);
									}
								} else if (genClass.isESetFlag(genFeature)) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_574);
									stringBuffer.append(genFeature.getCapName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_575);
									stringBuffer.append(genClass.getESetFlagsField(genFeature));
									stringBuffer.append(JavaClassGenerator.this.TEXT_576);
									stringBuffer.append(genFeature.getUpperName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_577);
									if (!genModel.isSuppressNotification()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_578);
										stringBuffer.append(genClass.getESetFlagsField(genFeature));
										stringBuffer.append(JavaClassGenerator.this.TEXT_579);
										stringBuffer.append(genFeature.getUpperName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_580);
									}
								} else {
									if (!genModel.isSuppressNotification()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_581);
										stringBuffer.append(genFeature.getCapName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_582);
										stringBuffer.append(genFeature.getUncapName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_583);
									}
									stringBuffer.append(JavaClassGenerator.this.TEXT_584);
									stringBuffer.append(genFeature.getUncapName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_585);
								}
							}
							if (!genModel.isSuppressNotification()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_586);
								if (genFeature.isUnsettable()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_587);
									stringBuffer.append(
											genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
									stringBuffer.append(JavaClassGenerator.this.TEXT_588);
									stringBuffer.append(
											genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
									stringBuffer.append(JavaClassGenerator.this.TEXT_589);
									stringBuffer.append(
											genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
									stringBuffer.append(JavaClassGenerator.this.TEXT_590);
									stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
									stringBuffer.append(positiveOffsetCorrection);
									stringBuffer.append(JavaClassGenerator.this.TEXT_591);
									if (genModel.isVirtualDelegation()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_592);
										stringBuffer.append(genFeature.getCapName());
									} else {
										stringBuffer.append(JavaClassGenerator.this.TEXT_593);
										stringBuffer.append(genFeature.getCapName());
									}
									stringBuffer.append(JavaClassGenerator.this.TEXT_594);
									stringBuffer.append(genFeature.getCapName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_595);
									if (genModel.isVirtualDelegation()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_596);
									} else {
										stringBuffer.append(JavaClassGenerator.this.TEXT_597);
										stringBuffer.append(genFeature.getCapName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_598);
									}
									stringBuffer.append(JavaClassGenerator.this.TEXT_599);
								} else {
									stringBuffer.append(JavaClassGenerator.this.TEXT_600);
									stringBuffer.append(
											genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
									stringBuffer.append(JavaClassGenerator.this.TEXT_601);
									stringBuffer.append(
											genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
									stringBuffer.append(JavaClassGenerator.this.TEXT_602);
									stringBuffer.append(
											genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
									stringBuffer.append(JavaClassGenerator.this.TEXT_603);
									stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
									stringBuffer.append(positiveOffsetCorrection);
									stringBuffer.append(JavaClassGenerator.this.TEXT_604);
									if (genModel.isVirtualDelegation()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_605);
										stringBuffer.append(genFeature.getCapName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_606);
										stringBuffer.append(genFeature.getCapName());
									} else {
										stringBuffer.append(JavaClassGenerator.this.TEXT_607);
										stringBuffer.append(genFeature.getCapName());
									}
									stringBuffer.append(JavaClassGenerator.this.TEXT_608);
									stringBuffer.append(genFeature.getCapName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_609);
								}
								stringBuffer.append(JavaClassGenerator.this.TEXT_610);
							}
							stringBuffer.append(JavaClassGenerator.this.TEXT_611);
						} else if (genFeature.hasDelegateFeature()) {
							final GenFeature delegateFeature = genFeature.getDelegateFeature();
							if (delegateFeature.isWrappedFeatureMapType()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_612);
								stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
								stringBuffer.append(JavaClassGenerator.this.TEXT_613);
								stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
								stringBuffer.append(JavaClassGenerator.this.TEXT_614);
								stringBuffer.append(delegateFeature.getAccessorName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_615);
								stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
								stringBuffer.append(JavaClassGenerator.this.TEXT_616);
								stringBuffer.append(genFeature.getCapName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_617);
							} else {
								stringBuffer.append(JavaClassGenerator.this.TEXT_618);
								stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
								stringBuffer.append(JavaClassGenerator.this.TEXT_619);
								stringBuffer.append(delegateFeature.getAccessorName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_620);
								stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
								stringBuffer.append(JavaClassGenerator.this.TEXT_621);
								stringBuffer.append(genFeature.getCapName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_622);
							}
						} else {
							stringBuffer.append(JavaClassGenerator.this.TEXT_623);
							stringBuffer.append(genFeature.getFormattedName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_624);
							stringBuffer.append(genFeature.getFeatureKind());
							stringBuffer.append(JavaClassGenerator.this.TEXT_625);
							// Class/basicSetGenFeature.todo.override.javajetinc
						}
						stringBuffer.append(JavaClassGenerator.this.TEXT_626);
						// Class/basicSetGenFeature.override.javajetinc
					}
					if (genFeature.isSet() && (isImplementation || !genFeature.isSuppressedSetVisibility())) {
						if (isInterface) {
							stringBuffer.append(JavaClassGenerator.this.TEXT_627);
							stringBuffer.append(genClass.getQualifiedInterfaceName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_628);
							stringBuffer.append(genFeature.getGetAccessor());
							stringBuffer.append(JavaClassGenerator.this.TEXT_629);
							stringBuffer.append(genFeature.getFormattedName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_630);
							stringBuffer.append(genFeature.getFeatureKind());
							stringBuffer.append(JavaClassGenerator.this.TEXT_631);
							stringBuffer.append(JavaClassGenerator.this.TEXT_632);
							stringBuffer.append(genFeature.getFormattedName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_633);
							stringBuffer.append(genFeature.getFeatureKind());
							stringBuffer.append(JavaClassGenerator.this.TEXT_634);
							if (genFeature.isEnumType()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_635);
								stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
							}
							if (genFeature.isUnsettable()) {
								if (!genFeature.isSuppressedIsSetVisibility()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_636);
									stringBuffer.append(genFeature.getAccessorName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_637);
								}
								if (!genFeature.isSuppressedUnsetVisibility()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_638);
									stringBuffer.append(genFeature.getAccessorName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_639);
								}
							}
							stringBuffer.append(JavaClassGenerator.this.TEXT_640);
							stringBuffer.append(genFeature.getGetAccessor());
							stringBuffer.append(JavaClassGenerator.this.TEXT_641);
							// Class/setGenFeature.javadoc.override.javajetinc
						} else {
							stringBuffer.append(JavaClassGenerator.this.TEXT_642);
							if (isJDK50) { // Class/setGenFeature.annotations.insert.javajetinc
							}
						}
						if (!isImplementation) {
							stringBuffer.append(JavaClassGenerator.this.TEXT_643);
							stringBuffer.append(genFeature.getAccessorName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_644);
							stringBuffer.append(genFeature.getImportedType(genClass));
							stringBuffer.append(JavaClassGenerator.this.TEXT_645);
						} else {
							final GenOperation setAccessorOperation = genClass.getSetAccessorOperation(genFeature);
							stringBuffer.append(JavaClassGenerator.this.TEXT_646);
							stringBuffer.append(genFeature.getAccessorName());
							if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_647);
							}
							stringBuffer.append(JavaClassGenerator.this.TEXT_648);
							stringBuffer.append(genFeature.getImportedType(genClass));
							stringBuffer.append(JavaClassGenerator.this.TEXT_649);
							stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName()
							: setAccessorOperation.getGenParameters().get(0).getName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_650);

							if (genModel.isDynamicDelegation()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_651);
								stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
								stringBuffer.append(JavaClassGenerator.this.TEXT_652);
								stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
								stringBuffer.append(JavaClassGenerator.this.TEXT_653);
								if (!isJDK50 && genFeature.isPrimitiveType()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_654);
									stringBuffer.append(genFeature.getObjectType(genClass));
									stringBuffer.append(JavaClassGenerator.this.TEXT_655);
								}
								stringBuffer.append(JavaClassGenerator.this.TEXT_656);
								stringBuffer.append(genFeature.getCapName());
								if (!isJDK50 && genFeature.isPrimitiveType()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_657);
								}
								stringBuffer.append(JavaClassGenerator.this.TEXT_658);
							} else if (genModel.isReflectiveDelegation()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_659);
								stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
								stringBuffer.append(JavaClassGenerator.this.TEXT_660);
								if (!isJDK50 && genFeature.isPrimitiveType()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_661);
									stringBuffer.append(genFeature.getObjectType(genClass));
									stringBuffer.append(JavaClassGenerator.this.TEXT_662);
								}
								stringBuffer.append(JavaClassGenerator.this.TEXT_663);
								stringBuffer.append(genFeature.getCapName());
								if (!isJDK50 && genFeature.isPrimitiveType()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_664);
								}
								stringBuffer.append(JavaClassGenerator.this.TEXT_665);
							} else if (genFeature.hasSettingDelegate()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_666);
								stringBuffer.append(genFeature.getUpperName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_667);
								if (!isJDK50 && genFeature.isPrimitiveType()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_668);
									stringBuffer.append(genFeature.getObjectType(genClass));
									stringBuffer.append(JavaClassGenerator.this.TEXT_669);
								}
								stringBuffer.append(JavaClassGenerator.this.TEXT_670);
								stringBuffer.append(genFeature.getCapName());
								if (!isJDK50 && genFeature.isPrimitiveType()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_671);
								}
								stringBuffer.append(JavaClassGenerator.this.TEXT_672);
							} else if (!genFeature.isVolatile()) {
								if (genFeature.isContainer()) {
									final GenFeature reverseFeature = genFeature.getReverse();
									final GenClass targetClass = reverseFeature.getGenClass();
									final String reverseOffsetCorrection = targetClass.hasOffsetCorrection()
											? " + " + genClass.getOffsetCorrectionField(genFeature)
											: "";
									stringBuffer.append(JavaClassGenerator.this.TEXT_673);
									stringBuffer.append(genFeature.getCapName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_674);
									stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
									stringBuffer.append(positiveOffsetCorrection);
									stringBuffer.append(JavaClassGenerator.this.TEXT_675);
									stringBuffer.append(genFeature.getCapName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_676);
									stringBuffer
									.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
									stringBuffer.append(JavaClassGenerator.this.TEXT_677);
									stringBuffer.append(genFeature.getEObjectCast());
									stringBuffer.append(JavaClassGenerator.this.TEXT_678);
									stringBuffer.append(genFeature.getCapName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_679);
									stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
									stringBuffer.append(JavaClassGenerator.this.TEXT_680);
									stringBuffer.append(genModel.getNonNLS());
									stringBuffer.append(JavaClassGenerator.this.TEXT_681);
									stringBuffer.append(genModel
											.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
									stringBuffer.append(JavaClassGenerator.this.TEXT_682);
									stringBuffer.append(genFeature.getCapName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_683);
									stringBuffer
									.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
									stringBuffer.append(JavaClassGenerator.this.TEXT_684);
									stringBuffer.append(genFeature.getCapName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_685);
									stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
									stringBuffer.append(reverseOffsetCorrection);
									stringBuffer.append(JavaClassGenerator.this.TEXT_686);
									stringBuffer.append(targetClass.getRawImportedInterfaceName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_687);
									stringBuffer.append(genFeature.getAccessorName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_688);
									stringBuffer.append(genFeature.getInternalTypeCast());
									stringBuffer.append(JavaClassGenerator.this.TEXT_689);
									stringBuffer.append(genFeature.getCapName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_690);
									if (!genModel.isSuppressNotification()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_691);
										stringBuffer.append(genModel
												.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
										stringBuffer.append(JavaClassGenerator.this.TEXT_692);
										stringBuffer.append(
												genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
										stringBuffer.append(JavaClassGenerator.this.TEXT_693);
										stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
										stringBuffer.append(positiveOffsetCorrection);
										stringBuffer.append(JavaClassGenerator.this.TEXT_694);
										stringBuffer.append(genFeature.getCapName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_695);
										stringBuffer.append(genFeature.getCapName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_696);
									}
								} else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
									if (genModel.isVirtualDelegation()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_697);
										stringBuffer.append(genFeature.getImportedType(genClass));
										stringBuffer.append(JavaClassGenerator.this.TEXT_698);
										stringBuffer.append(genFeature.getSafeName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_699);
										stringBuffer.append(genFeature.getImportedType(genClass));
										stringBuffer.append(JavaClassGenerator.this.TEXT_700);
										stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
										stringBuffer.append(positiveOffsetCorrection);
										stringBuffer.append(JavaClassGenerator.this.TEXT_701);
									}
									stringBuffer.append(JavaClassGenerator.this.TEXT_702);
									stringBuffer.append(genFeature.getCapName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_703);
									stringBuffer.append(genFeature.getSafeName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_704);
									stringBuffer.append(genModel
											.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
									stringBuffer.append(JavaClassGenerator.this.TEXT_705);
									stringBuffer.append(genFeature.getSafeName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_706);
									if (!genFeature.isBidirectional()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_707);
										stringBuffer.append(
												genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
										stringBuffer.append(JavaClassGenerator.this.TEXT_708);
										stringBuffer.append(genFeature.getSafeName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_709);
										stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
										stringBuffer.append(negativeOffsetCorrection);
										stringBuffer.append(JavaClassGenerator.this.TEXT_710);
										stringBuffer.append(genFeature.getCapName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_711);
										stringBuffer.append(
												genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
										stringBuffer.append(JavaClassGenerator.this.TEXT_712);
										stringBuffer.append(genFeature.getCapName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_713);
										stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
										stringBuffer.append(negativeOffsetCorrection);
										stringBuffer.append(JavaClassGenerator.this.TEXT_714);
									} else {
										final GenFeature reverseFeature = genFeature.getReverse();
										final GenClass targetClass = reverseFeature.getGenClass();
										final String reverseOffsetCorrection = targetClass.hasOffsetCorrection()
												? " + " + genClass.getOffsetCorrectionField(genFeature)
												: "";
										stringBuffer.append(JavaClassGenerator.this.TEXT_715);
										stringBuffer.append(
												genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
										stringBuffer.append(JavaClassGenerator.this.TEXT_716);
										stringBuffer.append(genFeature.getSafeName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_717);
										stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
										stringBuffer.append(reverseOffsetCorrection);
										stringBuffer.append(JavaClassGenerator.this.TEXT_718);
										stringBuffer.append(targetClass.getRawImportedInterfaceName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_719);
										stringBuffer.append(genFeature.getCapName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_720);
										stringBuffer.append(
												genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
										stringBuffer.append(JavaClassGenerator.this.TEXT_721);
										stringBuffer.append(genFeature.getCapName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_722);
										stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
										stringBuffer.append(reverseOffsetCorrection);
										stringBuffer.append(JavaClassGenerator.this.TEXT_723);
										stringBuffer.append(targetClass.getRawImportedInterfaceName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_724);
									}
									stringBuffer.append(JavaClassGenerator.this.TEXT_725);
									stringBuffer.append(genFeature.getAccessorName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_726);
									stringBuffer.append(genFeature.getInternalTypeCast());
									stringBuffer.append(JavaClassGenerator.this.TEXT_727);
									stringBuffer.append(genFeature.getCapName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_728);
									if (genFeature.isUnsettable()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_729);
										if (genModel.isVirtualDelegation()) {
											stringBuffer.append(JavaClassGenerator.this.TEXT_730);
											stringBuffer.append(genFeature.getCapName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_731);
											stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
											stringBuffer.append(positiveOffsetCorrection);
											stringBuffer.append(JavaClassGenerator.this.TEXT_732);
										} else if (genClass.isESetFlag(genFeature)) {
											if (!genModel.isSuppressNotification()) {
												stringBuffer.append(JavaClassGenerator.this.TEXT_733);
												stringBuffer.append(genFeature.getCapName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_734);
												stringBuffer.append(genClass.getESetFlagsField(genFeature));
												stringBuffer.append(JavaClassGenerator.this.TEXT_735);
												stringBuffer.append(genFeature.getUpperName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_736);
											}
											stringBuffer.append(JavaClassGenerator.this.TEXT_737);
											stringBuffer.append(genClass.getESetFlagsField(genFeature));
											stringBuffer.append(JavaClassGenerator.this.TEXT_738);
											stringBuffer.append(genFeature.getUpperName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_739);
										} else {
											if (!genModel.isSuppressNotification()) {
												stringBuffer.append(JavaClassGenerator.this.TEXT_740);
												stringBuffer.append(genFeature.getCapName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_741);
												stringBuffer.append(genFeature.getUncapName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_742);
											}
											stringBuffer.append(JavaClassGenerator.this.TEXT_743);
											stringBuffer.append(genFeature.getUncapName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_744);
										}
										if (!genModel.isSuppressNotification()) {
											stringBuffer.append(JavaClassGenerator.this.TEXT_745);
											stringBuffer.append(genModel
													.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
											stringBuffer.append(JavaClassGenerator.this.TEXT_746);
											stringBuffer.append(genModel
													.getImportedName("org.eclipse.emf.common.notify.Notification"));
											stringBuffer.append(JavaClassGenerator.this.TEXT_747);
											stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
											stringBuffer.append(positiveOffsetCorrection);
											stringBuffer.append(JavaClassGenerator.this.TEXT_748);
											stringBuffer.append(genFeature.getCapName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_749);
											stringBuffer.append(genFeature.getCapName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_750);
											stringBuffer.append(genFeature.getCapName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_751);
										}
										stringBuffer.append(JavaClassGenerator.this.TEXT_752);
									} else if (!genModel.isSuppressNotification()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_753);
										stringBuffer.append(genModel
												.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
										stringBuffer.append(JavaClassGenerator.this.TEXT_754);
										stringBuffer.append(genModel
												.getImportedName("org.eclipse.emf.common.notify.Notification"));
										stringBuffer.append(JavaClassGenerator.this.TEXT_755);
										stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
										stringBuffer.append(positiveOffsetCorrection);
										stringBuffer.append(JavaClassGenerator.this.TEXT_756);
										stringBuffer.append(genFeature.getCapName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_757);
										stringBuffer.append(genFeature.getCapName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_758);
									}
								} else {
									if (genClass.isFlag(genFeature)) {
										if (!genModel.isSuppressNotification()) {
											if (genFeature.isBooleanType()) {
												stringBuffer.append(JavaClassGenerator.this.TEXT_759);
												stringBuffer.append(genFeature.getImportedType(genClass));
												stringBuffer.append(JavaClassGenerator.this.TEXT_760);
												stringBuffer.append(genFeature.getCapName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_761);
												stringBuffer.append(genClass.getFlagsField(genFeature));
												stringBuffer.append(JavaClassGenerator.this.TEXT_762);
												stringBuffer.append(genFeature.getUpperName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_763);
											} else {
												stringBuffer.append(JavaClassGenerator.this.TEXT_764);
												stringBuffer.append(genFeature.getImportedType(genClass));
												stringBuffer.append(JavaClassGenerator.this.TEXT_765);
												stringBuffer.append(genFeature.getCapName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_766);
												stringBuffer.append(genFeature.getUpperName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_767);
												stringBuffer.append(genClass.getFlagsField(genFeature));
												stringBuffer.append(JavaClassGenerator.this.TEXT_768);
												stringBuffer.append(genFeature.getUpperName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_769);
												stringBuffer.append(genFeature.getUpperName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_770);
											}
										}
										if (genFeature.isBooleanType()) {
											stringBuffer.append(JavaClassGenerator.this.TEXT_771);
											stringBuffer.append(genFeature.getCapName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_772);
											stringBuffer.append(genClass.getFlagsField(genFeature));
											stringBuffer.append(JavaClassGenerator.this.TEXT_773);
											stringBuffer.append(genFeature.getUpperName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_774);
											stringBuffer.append(genClass.getFlagsField(genFeature));
											stringBuffer.append(JavaClassGenerator.this.TEXT_775);
											stringBuffer.append(genFeature.getUpperName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_776);
										} else {
											stringBuffer.append(JavaClassGenerator.this.TEXT_777);
											stringBuffer.append(genFeature.getCapName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_778);
											stringBuffer.append(genFeature.getCapName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_779);
											stringBuffer.append(genFeature.getUpperName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_780);
											stringBuffer.append(genClass.getFlagsField(genFeature));
											stringBuffer.append(JavaClassGenerator.this.TEXT_781);
											stringBuffer.append(genClass.getFlagsField(genFeature));
											stringBuffer.append(JavaClassGenerator.this.TEXT_782);
											stringBuffer.append(genFeature.getUpperName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_783);
											if (isJDK50) {
												stringBuffer.append(JavaClassGenerator.this.TEXT_784);
												stringBuffer.append(genFeature.getCapName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_785);
											} else {
												stringBuffer.append(genFeature.getImportedType(genClass));
												stringBuffer.append(JavaClassGenerator.this.TEXT_786);
												stringBuffer.append(genFeature.getCapName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_787);
											}
											stringBuffer.append(JavaClassGenerator.this.TEXT_788);
											stringBuffer.append(genFeature.getUpperName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_789);
										}
									} else {
										if ((!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) && !genModel.isSuppressNotification()) {
											stringBuffer.append(JavaClassGenerator.this.TEXT_790);
											stringBuffer.append(genFeature.getImportedType(genClass));
											stringBuffer.append(JavaClassGenerator.this.TEXT_791);
											stringBuffer.append(genFeature.getCapName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_792);
											stringBuffer.append(genFeature.getSafeName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_793);
										}
										if (genFeature.isEnumType()) {
											if (genModel.isVirtualDelegation()) {
												stringBuffer.append(JavaClassGenerator.this.TEXT_794);
												stringBuffer.append(genFeature.getImportedType(genClass));
												stringBuffer.append(JavaClassGenerator.this.TEXT_795);
												stringBuffer.append(genFeature.getSafeName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_796);
												stringBuffer.append(genFeature.getCapName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_797);
												stringBuffer.append(genFeature.getEDefault());
												stringBuffer.append(JavaClassGenerator.this.TEXT_798);
												stringBuffer.append(genFeature.getCapName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_799);
											} else {
												stringBuffer.append(JavaClassGenerator.this.TEXT_800);
												stringBuffer.append(genFeature.getSafeName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_801);
												stringBuffer.append(genFeature.getCapName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_802);
												stringBuffer.append(genFeature.getEDefault());
												stringBuffer.append(JavaClassGenerator.this.TEXT_803);
												stringBuffer.append(genFeature.getCapName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_804);
											}
										} else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
											stringBuffer.append(JavaClassGenerator.this.TEXT_805);
											stringBuffer.append(genFeature.getImportedType(genClass));
											stringBuffer.append(JavaClassGenerator.this.TEXT_806);
											stringBuffer.append(genFeature.getSafeName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_807);
											stringBuffer.append(genFeature.getInternalTypeCast());
											stringBuffer.append(JavaClassGenerator.this.TEXT_808);
											stringBuffer.append(genFeature.getCapName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_809);
										} else {
											stringBuffer.append(JavaClassGenerator.this.TEXT_810);
											stringBuffer.append(genFeature.getSafeName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_811);
											stringBuffer.append(genFeature.getInternalTypeCast());
											stringBuffer.append(JavaClassGenerator.this.TEXT_812);
											stringBuffer.append(genFeature.getCapName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_813);
										}
										if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
											stringBuffer.append(JavaClassGenerator.this.TEXT_814);
											stringBuffer.append(genFeature.getCapName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_815);
											stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
											stringBuffer.append(positiveOffsetCorrection);
											stringBuffer.append(JavaClassGenerator.this.TEXT_816);
											stringBuffer.append(genFeature.getSafeName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_817);
										}
									}
									if (genFeature.isUnsettable()) {
										if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
											stringBuffer.append(JavaClassGenerator.this.TEXT_818);
											stringBuffer.append(genFeature.getCapName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_819);
										} else if (genClass.isESetFlag(genFeature)) {
											if (!genModel.isSuppressNotification()) {
												stringBuffer.append(JavaClassGenerator.this.TEXT_820);
												stringBuffer.append(genFeature.getCapName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_821);
												stringBuffer.append(genClass.getESetFlagsField(genFeature));
												stringBuffer.append(JavaClassGenerator.this.TEXT_822);
												stringBuffer.append(genFeature.getUpperName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_823);
											}
											stringBuffer.append(JavaClassGenerator.this.TEXT_824);
											stringBuffer.append(genClass.getESetFlagsField(genFeature));
											stringBuffer.append(JavaClassGenerator.this.TEXT_825);
											stringBuffer.append(genFeature.getUpperName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_826);
										} else {
											if (!genModel.isSuppressNotification()) {
												stringBuffer.append(JavaClassGenerator.this.TEXT_827);
												stringBuffer.append(genFeature.getCapName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_828);
												stringBuffer.append(genFeature.getUncapName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_829);
											}
											stringBuffer.append(JavaClassGenerator.this.TEXT_830);
											stringBuffer.append(genFeature.getUncapName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_831);
										}
										if (!genModel.isSuppressNotification()) {
											stringBuffer.append(JavaClassGenerator.this.TEXT_832);
											stringBuffer.append(genModel
													.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
											stringBuffer.append(JavaClassGenerator.this.TEXT_833);
											stringBuffer.append(genModel
													.getImportedName("org.eclipse.emf.common.notify.Notification"));
											stringBuffer.append(JavaClassGenerator.this.TEXT_834);
											stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
											stringBuffer.append(positiveOffsetCorrection);
											stringBuffer.append(JavaClassGenerator.this.TEXT_835);
											if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
												stringBuffer.append(JavaClassGenerator.this.TEXT_836);
												stringBuffer.append(genFeature.getEDefault());
												stringBuffer.append(JavaClassGenerator.this.TEXT_837);
												stringBuffer.append(genFeature.getCapName());
											} else {
												stringBuffer.append(JavaClassGenerator.this.TEXT_838);
												stringBuffer.append(genFeature.getCapName());
											}
											stringBuffer.append(JavaClassGenerator.this.TEXT_839);
											if (genClass.isFlag(genFeature)) {
												stringBuffer.append(JavaClassGenerator.this.TEXT_840);
												stringBuffer.append(genFeature.getCapName());
											} else {
												stringBuffer.append(genFeature.getSafeName());
											}
											stringBuffer.append(JavaClassGenerator.this.TEXT_841);
											if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
												stringBuffer.append(JavaClassGenerator.this.TEXT_842);
											} else {
												stringBuffer.append(JavaClassGenerator.this.TEXT_843);
												stringBuffer.append(genFeature.getCapName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_844);
											}
											stringBuffer.append(JavaClassGenerator.this.TEXT_845);
										}
									} else if (!genModel.isSuppressNotification()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_846);
										stringBuffer.append(genModel
												.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
										stringBuffer.append(JavaClassGenerator.this.TEXT_847);
										stringBuffer.append(genModel
												.getImportedName("org.eclipse.emf.common.notify.Notification"));
										stringBuffer.append(JavaClassGenerator.this.TEXT_848);
										stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
										stringBuffer.append(positiveOffsetCorrection);
										stringBuffer.append(JavaClassGenerator.this.TEXT_849);
										if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
											stringBuffer.append(JavaClassGenerator.this.TEXT_850);
											stringBuffer.append(genFeature.getCapName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_851);
											stringBuffer.append(genFeature.getEDefault());
											stringBuffer.append(JavaClassGenerator.this.TEXT_852);
											stringBuffer.append(genFeature.getCapName());
										} else {
											stringBuffer.append(JavaClassGenerator.this.TEXT_853);
											stringBuffer.append(genFeature.getCapName());
										}
										stringBuffer.append(JavaClassGenerator.this.TEXT_854);
										if (genClass.isFlag(genFeature)) {
											stringBuffer.append(JavaClassGenerator.this.TEXT_855);
											stringBuffer.append(genFeature.getCapName());
										} else {
											stringBuffer.append(genFeature.getSafeName());
										}
										stringBuffer.append(JavaClassGenerator.this.TEXT_856);
									}
								}
							} else if (genFeature.hasDelegateFeature()) {
								final GenFeature delegateFeature = genFeature.getDelegateFeature();
								if (delegateFeature.isWrappedFeatureMapType()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_857);
									stringBuffer
									.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
									stringBuffer.append(JavaClassGenerator.this.TEXT_858);
									stringBuffer
									.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
									stringBuffer.append(JavaClassGenerator.this.TEXT_859);
									stringBuffer.append(delegateFeature.getAccessorName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_860);
									stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
									stringBuffer.append(JavaClassGenerator.this.TEXT_861);
									if (!isJDK50 && genFeature.isPrimitiveType()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_862);
										stringBuffer.append(genFeature.getObjectType(genClass));
										stringBuffer.append(JavaClassGenerator.this.TEXT_863);
									}
									stringBuffer.append(JavaClassGenerator.this.TEXT_864);
									stringBuffer.append(genFeature.getCapName());
									if (!isJDK50 && genFeature.isPrimitiveType()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_865);
									}
									stringBuffer.append(JavaClassGenerator.this.TEXT_866);
								} else {
									stringBuffer.append(JavaClassGenerator.this.TEXT_867);
									stringBuffer
									.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
									stringBuffer.append(JavaClassGenerator.this.TEXT_868);
									stringBuffer.append(delegateFeature.getAccessorName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_869);
									stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
									stringBuffer.append(JavaClassGenerator.this.TEXT_870);
									if (!isJDK50 && genFeature.isPrimitiveType()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_871);
										stringBuffer.append(genFeature.getObjectType(genClass));
										stringBuffer.append(JavaClassGenerator.this.TEXT_872);
									}
									stringBuffer.append(JavaClassGenerator.this.TEXT_873);
									stringBuffer.append(genFeature.getCapName());
									if (!isJDK50 && genFeature.isPrimitiveType()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_874);
									}
									stringBuffer.append(JavaClassGenerator.this.TEXT_875);
								}
							} else if (setAccessorOperation != null) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_876);
								stringBuffer
								.append(setAccessorOperation.getBody(genModel.getIndentation(stringBuffer)));
							} else {
								stringBuffer.append(JavaClassGenerator.this.TEXT_877);
								stringBuffer.append(genFeature.getFormattedName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_878);
								stringBuffer.append(genFeature.getFeatureKind());
								stringBuffer.append(JavaClassGenerator.this.TEXT_879);
								// Class/setGenFeature.todo.override.javajetinc
							}
							stringBuffer.append(JavaClassGenerator.this.TEXT_880);
						}
						// Class/setGenFeature.override.javajetinc
					}
					if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
						stringBuffer.append(JavaClassGenerator.this.TEXT_881);
						if (isJDK50) { // Class/basicUnsetGenFeature.annotations.insert.javajetinc
						}
						stringBuffer.append(JavaClassGenerator.this.TEXT_882);
						stringBuffer
						.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
						stringBuffer.append(JavaClassGenerator.this.TEXT_883);
						stringBuffer.append(genFeature.getAccessorName());
						stringBuffer.append(JavaClassGenerator.this.TEXT_884);
						stringBuffer
						.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
						stringBuffer.append(JavaClassGenerator.this.TEXT_885);
						if (genModel.isDynamicDelegation()) {
							stringBuffer.append(JavaClassGenerator.this.TEXT_886);
							stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
							stringBuffer.append(JavaClassGenerator.this.TEXT_887);
							if (genFeature.isResolveProxies()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_888);
								stringBuffer.append(genFeature.getAccessorName());
							} else {
								stringBuffer.append(genFeature.getGetAccessor());
							}
							stringBuffer.append(JavaClassGenerator.this.TEXT_889);
							stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
							stringBuffer.append(positiveOffsetCorrection);
							stringBuffer.append(JavaClassGenerator.this.TEXT_890);
						} else if (!genFeature.isVolatile()) {
							if (genModel.isVirtualDelegation()) {
								if (!genModel.isSuppressNotification()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_891);
									stringBuffer.append(genFeature.getCapName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_892);
								}
								stringBuffer.append(JavaClassGenerator.this.TEXT_893);
								stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
								stringBuffer.append(positiveOffsetCorrection);
								stringBuffer.append(JavaClassGenerator.this.TEXT_894);
							} else {
								if (!genModel.isSuppressNotification()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_895);
									stringBuffer.append(genFeature.getImportedType(genClass));
									stringBuffer.append(JavaClassGenerator.this.TEXT_896);
									stringBuffer.append(genFeature.getCapName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_897);
									stringBuffer.append(genFeature.getSafeName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_898);
								}
								stringBuffer.append(JavaClassGenerator.this.TEXT_899);
								stringBuffer.append(genFeature.getSafeName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_900);
							}
							if (genModel.isVirtualDelegation()) {
								if (!genModel.isSuppressNotification()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_901);
									stringBuffer.append(genFeature.getCapName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_902);
								}
							} else if (genClass.isESetFlag(genFeature)) {
								if (!genModel.isSuppressNotification()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_903);
									stringBuffer.append(genFeature.getCapName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_904);
									stringBuffer.append(genClass.getESetFlagsField(genFeature));
									stringBuffer.append(JavaClassGenerator.this.TEXT_905);
									stringBuffer.append(genFeature.getUpperName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_906);
								}
								stringBuffer.append(JavaClassGenerator.this.TEXT_907);
								stringBuffer.append(genClass.getESetFlagsField(genFeature));
								stringBuffer.append(JavaClassGenerator.this.TEXT_908);
								stringBuffer.append(genFeature.getUpperName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_909);
							} else {
								if (!genModel.isSuppressNotification()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_910);
									stringBuffer.append(genFeature.getCapName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_911);
									stringBuffer.append(genFeature.getUncapName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_912);
								}
								stringBuffer.append(JavaClassGenerator.this.TEXT_913);
								stringBuffer.append(genFeature.getUncapName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_914);
							}
							if (!genModel.isSuppressNotification()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_915);
								stringBuffer.append(
										genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
								stringBuffer.append(JavaClassGenerator.this.TEXT_916);
								stringBuffer.append(
										genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
								stringBuffer.append(JavaClassGenerator.this.TEXT_917);
								stringBuffer
								.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
								stringBuffer.append(JavaClassGenerator.this.TEXT_918);
								stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
								stringBuffer.append(positiveOffsetCorrection);
								stringBuffer.append(JavaClassGenerator.this.TEXT_919);
								if (genModel.isVirtualDelegation()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_920);
									stringBuffer.append(genFeature.getCapName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_921);
								} else {
									stringBuffer.append(JavaClassGenerator.this.TEXT_922);
									stringBuffer.append(genFeature.getCapName());
								}
								stringBuffer.append(JavaClassGenerator.this.TEXT_923);
								if (genModel.isVirtualDelegation()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_924);
								} else {
									stringBuffer.append(JavaClassGenerator.this.TEXT_925);
									stringBuffer.append(genFeature.getCapName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_926);
								}
								stringBuffer.append(JavaClassGenerator.this.TEXT_927);
							}
						} else {
							stringBuffer.append(JavaClassGenerator.this.TEXT_928);
							stringBuffer.append(genFeature.getFormattedName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_929);
							stringBuffer.append(genFeature.getFeatureKind());
							stringBuffer.append(JavaClassGenerator.this.TEXT_930);
							// Class/basicUnsetGenFeature.todo.override.javajetinc
						}
						stringBuffer.append(JavaClassGenerator.this.TEXT_931);
						// Class.basicUnsetGenFeature.override.javajetinc
					}
					if (genFeature.isUnset() && (isImplementation || !genFeature.isSuppressedUnsetVisibility())) {
						if (isInterface) {
							stringBuffer.append(JavaClassGenerator.this.TEXT_932);
							stringBuffer.append(genClass.getQualifiedInterfaceName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_933);
							stringBuffer.append(genFeature.getGetAccessor());
							stringBuffer.append(JavaClassGenerator.this.TEXT_934);
							stringBuffer.append(genFeature.getFormattedName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_935);
							stringBuffer.append(genFeature.getFeatureKind());
							stringBuffer.append(JavaClassGenerator.this.TEXT_936);
							stringBuffer.append(JavaClassGenerator.this.TEXT_937);
							if (!genFeature.isSuppressedIsSetVisibility()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_938);
								stringBuffer.append(genFeature.getAccessorName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_939);
							}
							stringBuffer.append(JavaClassGenerator.this.TEXT_940);
							stringBuffer.append(genFeature.getGetAccessor());
							stringBuffer.append(JavaClassGenerator.this.TEXT_941);
							if (!genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_942);
								stringBuffer.append(genFeature.getAccessorName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_943);
								stringBuffer.append(genFeature.getRawImportedBoundType());
								stringBuffer.append(JavaClassGenerator.this.TEXT_944);
							}
							stringBuffer.append(JavaClassGenerator.this.TEXT_945);
							// Class/unsetGenFeature.javadoc.override.javajetinc
						} else {
							stringBuffer.append(JavaClassGenerator.this.TEXT_946);
							if (isJDK50) { // Class/unsetGenFeature.annotations.insert.javajetinc
							}
						}
						if (!isImplementation) {
							stringBuffer.append(JavaClassGenerator.this.TEXT_947);
							stringBuffer.append(genFeature.getAccessorName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_948);
						} else {
							stringBuffer.append(JavaClassGenerator.this.TEXT_949);
							stringBuffer.append(genFeature.getAccessorName());
							if (genClass.hasCollidingUnsetAccessorOperation(genFeature)) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_950);
							}
							stringBuffer.append(JavaClassGenerator.this.TEXT_951);
							if (genModel.isDynamicDelegation()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_952);
								stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
								stringBuffer.append(JavaClassGenerator.this.TEXT_953);
								stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
								stringBuffer.append(JavaClassGenerator.this.TEXT_954);
							} else if (genModel.isReflectiveDelegation()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_955);
								stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
								stringBuffer.append(JavaClassGenerator.this.TEXT_956);
							} else if (genFeature.hasSettingDelegate()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_957);
								stringBuffer.append(genFeature.getUpperName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_958);
							} else if (!genFeature.isVolatile()) {
								if (genFeature.isListType()) {
									if (genModel.isVirtualDelegation()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_959);
										stringBuffer.append(genFeature.getImportedType(genClass));
										stringBuffer.append(JavaClassGenerator.this.TEXT_960);
										stringBuffer.append(genFeature.getSafeName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_961);
										stringBuffer.append(genFeature.getImportedType(genClass));
										stringBuffer.append(JavaClassGenerator.this.TEXT_962);
										stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
										stringBuffer.append(positiveOffsetCorrection);
										stringBuffer.append(JavaClassGenerator.this.TEXT_963);
									}
									stringBuffer.append(JavaClassGenerator.this.TEXT_964);
									stringBuffer.append(genFeature.getSafeName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_965);
									stringBuffer.append(
											genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
									stringBuffer.append(JavaClassGenerator.this.TEXT_966);
									stringBuffer.append(singleWildcard);
									stringBuffer.append(JavaClassGenerator.this.TEXT_967);
									stringBuffer.append(genFeature.getSafeName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_968);
								} else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
									if (genModel.isVirtualDelegation()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_969);
										stringBuffer.append(genFeature.getImportedType(genClass));
										stringBuffer.append(JavaClassGenerator.this.TEXT_970);
										stringBuffer.append(genFeature.getSafeName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_971);
										stringBuffer.append(genFeature.getImportedType(genClass));
										stringBuffer.append(JavaClassGenerator.this.TEXT_972);
										stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
										stringBuffer.append(positiveOffsetCorrection);
										stringBuffer.append(JavaClassGenerator.this.TEXT_973);
									}
									stringBuffer.append(JavaClassGenerator.this.TEXT_974);
									stringBuffer.append(genFeature.getSafeName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_975);
									stringBuffer.append(genModel
											.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
									stringBuffer.append(JavaClassGenerator.this.TEXT_976);
									if (!genFeature.isBidirectional()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_977);
										stringBuffer.append(
												genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
										stringBuffer.append(JavaClassGenerator.this.TEXT_978);
										stringBuffer.append(genFeature.getSafeName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_979);
										stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
										stringBuffer.append(negativeOffsetCorrection);
										stringBuffer.append(JavaClassGenerator.this.TEXT_980);
									} else {
										final GenFeature reverseFeature = genFeature.getReverse();
										final GenClass targetClass = reverseFeature.getGenClass();
										final String reverseOffsetCorrection = targetClass.hasOffsetCorrection()
												? " + " + genClass.getOffsetCorrectionField(genFeature)
												: "";
										stringBuffer.append(JavaClassGenerator.this.TEXT_981);
										stringBuffer.append(
												genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
										stringBuffer.append(JavaClassGenerator.this.TEXT_982);
										stringBuffer.append(genFeature.getSafeName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_983);
										stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
										stringBuffer.append(reverseOffsetCorrection);
										stringBuffer.append(JavaClassGenerator.this.TEXT_984);
										stringBuffer.append(targetClass.getRawImportedInterfaceName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_985);
									}
									stringBuffer.append(JavaClassGenerator.this.TEXT_986);
									stringBuffer.append(genFeature.getAccessorName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_987);
									if (genModel.isVirtualDelegation()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_988);
										stringBuffer.append(genFeature.getCapName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_989);
										stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
										stringBuffer.append(positiveOffsetCorrection);
										stringBuffer.append(JavaClassGenerator.this.TEXT_990);
									} else if (genClass.isESetFlag(genFeature)) {
										if (!genModel.isSuppressNotification()) {
											stringBuffer.append(JavaClassGenerator.this.TEXT_991);
											stringBuffer.append(genFeature.getCapName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_992);
											stringBuffer.append(genClass.getESetFlagsField(genFeature));
											stringBuffer.append(JavaClassGenerator.this.TEXT_993);
											stringBuffer.append(genFeature.getUpperName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_994);
										}
										stringBuffer.append(JavaClassGenerator.this.TEXT_995);
										stringBuffer.append(genClass.getESetFlagsField(genFeature));
										stringBuffer.append(JavaClassGenerator.this.TEXT_996);
										stringBuffer.append(genFeature.getUpperName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_997);
									} else {
										if (!genModel.isSuppressNotification()) {
											stringBuffer.append(JavaClassGenerator.this.TEXT_998);
											stringBuffer.append(genFeature.getCapName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_999);
											stringBuffer.append(genFeature.getUncapName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_1000);
										}
										stringBuffer.append(JavaClassGenerator.this.TEXT_1001);
										stringBuffer.append(genFeature.getUncapName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_1002);
									}
									if (!genModel.isSuppressNotification()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_1003);
										stringBuffer.append(genModel
												.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
										stringBuffer.append(JavaClassGenerator.this.TEXT_1004);
										stringBuffer.append(
												genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
										stringBuffer.append(JavaClassGenerator.this.TEXT_1005);
										stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
										stringBuffer.append(positiveOffsetCorrection);
										stringBuffer.append(JavaClassGenerator.this.TEXT_1006);
										stringBuffer.append(genFeature.getCapName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_1007);
									}
									stringBuffer.append(JavaClassGenerator.this.TEXT_1008);
								} else {
									if (genClass.isFlag(genFeature)) {
										if (!genModel.isSuppressNotification()) {
											if (genFeature.isBooleanType()) {
												stringBuffer.append(JavaClassGenerator.this.TEXT_1009);
												stringBuffer.append(genFeature.getImportedType(genClass));
												stringBuffer.append(JavaClassGenerator.this.TEXT_1010);
												stringBuffer.append(genFeature.getCapName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_1011);
												stringBuffer.append(genClass.getFlagsField(genFeature));
												stringBuffer.append(JavaClassGenerator.this.TEXT_1012);
												stringBuffer.append(genFeature.getUpperName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_1013);
											} else {
												stringBuffer.append(JavaClassGenerator.this.TEXT_1014);
												stringBuffer.append(genFeature.getImportedType(genClass));
												stringBuffer.append(JavaClassGenerator.this.TEXT_1015);
												stringBuffer.append(genFeature.getCapName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_1016);
												stringBuffer.append(genFeature.getUpperName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_1017);
												stringBuffer.append(genClass.getFlagsField(genFeature));
												stringBuffer.append(JavaClassGenerator.this.TEXT_1018);
												stringBuffer.append(genFeature.getUpperName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_1019);
												stringBuffer.append(genFeature.getUpperName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_1020);
											}
										}
									} else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_1021);
										stringBuffer.append(genFeature.getCapName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_1022);
										stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
										stringBuffer.append(positiveOffsetCorrection);
										stringBuffer.append(JavaClassGenerator.this.TEXT_1023);
									} else if (!genModel.isSuppressNotification()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_1024);
										stringBuffer.append(genFeature.getImportedType(genClass));
										stringBuffer.append(JavaClassGenerator.this.TEXT_1025);
										stringBuffer.append(genFeature.getCapName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_1026);
										stringBuffer.append(genFeature.getSafeName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_1027);
									}
									if (!genModel.isSuppressNotification()) {
										if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
											stringBuffer.append(JavaClassGenerator.this.TEXT_1028);
											stringBuffer.append(genFeature.getCapName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_1029);
										} else if (genClass.isESetFlag(genFeature)) {
											stringBuffer.append(JavaClassGenerator.this.TEXT_1030);
											stringBuffer.append(genFeature.getCapName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_1031);
											stringBuffer.append(genClass.getESetFlagsField(genFeature));
											stringBuffer.append(JavaClassGenerator.this.TEXT_1032);
											stringBuffer.append(genFeature.getUpperName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_1033);
										} else {
											stringBuffer.append(JavaClassGenerator.this.TEXT_1034);
											stringBuffer.append(genFeature.getCapName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_1035);
											stringBuffer.append(genFeature.getUncapName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_1036);
										}
									}
									if (genFeature.isReferenceType()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_1037);
										stringBuffer.append(genFeature.getSafeName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_1038);
										if (!genModel.isVirtualDelegation()) {
											if (genClass.isESetFlag(genFeature)) {
												stringBuffer.append(JavaClassGenerator.this.TEXT_1039);
												stringBuffer.append(genClass.getESetFlagsField(genFeature));
												stringBuffer.append(JavaClassGenerator.this.TEXT_1040);
												stringBuffer.append(genFeature.getUpperName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_1041);
											} else {
												stringBuffer.append(JavaClassGenerator.this.TEXT_1042);
												stringBuffer.append(genFeature.getUncapName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_1043);
											}
										}
										if (!genModel.isSuppressNotification()) {
											stringBuffer.append(JavaClassGenerator.this.TEXT_1044);
											stringBuffer.append(genModel
													.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
											stringBuffer.append(JavaClassGenerator.this.TEXT_1045);
											stringBuffer.append(genModel
													.getImportedName("org.eclipse.emf.common.notify.Notification"));
											stringBuffer.append(JavaClassGenerator.this.TEXT_1046);
											stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
											stringBuffer.append(positiveOffsetCorrection);
											stringBuffer.append(JavaClassGenerator.this.TEXT_1047);
											if (genModel.isVirtualDelegation()) {
												stringBuffer.append(JavaClassGenerator.this.TEXT_1048);
												stringBuffer.append(genFeature.getCapName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_1049);
											} else {
												stringBuffer.append(JavaClassGenerator.this.TEXT_1050);
												stringBuffer.append(genFeature.getCapName());
											}
											stringBuffer.append(JavaClassGenerator.this.TEXT_1051);
											if (genModel.isVirtualDelegation()) {
												stringBuffer.append(JavaClassGenerator.this.TEXT_1052);
											} else {
												stringBuffer.append(JavaClassGenerator.this.TEXT_1053);
												stringBuffer.append(genFeature.getCapName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_1054);
											}
											stringBuffer.append(JavaClassGenerator.this.TEXT_1055);
										}
									} else {
										if (genClass.isFlag(genFeature)) {
											if (genFeature.isBooleanType()) {
												stringBuffer.append(JavaClassGenerator.this.TEXT_1056);
												stringBuffer.append(genFeature.getEDefault());
												stringBuffer.append(JavaClassGenerator.this.TEXT_1057);
												stringBuffer.append(genClass.getFlagsField(genFeature));
												stringBuffer.append(JavaClassGenerator.this.TEXT_1058);
												stringBuffer.append(genFeature.getUpperName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_1059);
												stringBuffer.append(genClass.getFlagsField(genFeature));
												stringBuffer.append(JavaClassGenerator.this.TEXT_1060);
												stringBuffer.append(genFeature.getUpperName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_1061);
											} else {
												stringBuffer.append(JavaClassGenerator.this.TEXT_1062);
												stringBuffer.append(genClass.getFlagsField(genFeature));
												stringBuffer.append(JavaClassGenerator.this.TEXT_1063);
												stringBuffer.append(genClass.getFlagsField(genFeature));
												stringBuffer.append(JavaClassGenerator.this.TEXT_1064);
												stringBuffer.append(genFeature.getUpperName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_1065);
												stringBuffer.append(genFeature.getUpperName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_1066);
											}
										} else if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
											stringBuffer.append(JavaClassGenerator.this.TEXT_1067);
											stringBuffer.append(genFeature.getSafeName());
											stringBuffer.append(JavaClassGenerator.this.TEXT_1068);
											stringBuffer.append(genFeature.getEDefault());
											stringBuffer.append(JavaClassGenerator.this.TEXT_1069);
										}
										if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
											if (genClass.isESetFlag(genFeature)) {
												stringBuffer.append(JavaClassGenerator.this.TEXT_1070);
												stringBuffer.append(genClass.getESetFlagsField(genFeature));
												stringBuffer.append(JavaClassGenerator.this.TEXT_1071);
												stringBuffer.append(genFeature.getUpperName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_1072);
											} else {
												stringBuffer.append(JavaClassGenerator.this.TEXT_1073);
												stringBuffer.append(genFeature.getUncapName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_1074);
											}
										}
										if (!genModel.isSuppressNotification()) {
											stringBuffer.append(JavaClassGenerator.this.TEXT_1075);
											stringBuffer.append(genModel
													.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
											stringBuffer.append(JavaClassGenerator.this.TEXT_1076);
											stringBuffer.append(genModel
													.getImportedName("org.eclipse.emf.common.notify.Notification"));
											stringBuffer.append(JavaClassGenerator.this.TEXT_1077);
											stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
											stringBuffer.append(positiveOffsetCorrection);
											stringBuffer.append(JavaClassGenerator.this.TEXT_1078);
											if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
												stringBuffer.append(JavaClassGenerator.this.TEXT_1079);
												stringBuffer.append(genFeature.getCapName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_1080);
												stringBuffer.append(genFeature.getEDefault());
											} else {
												stringBuffer.append(JavaClassGenerator.this.TEXT_1081);
												stringBuffer.append(genFeature.getCapName());
											}
											stringBuffer.append(JavaClassGenerator.this.TEXT_1082);
											stringBuffer.append(genFeature.getEDefault());
											stringBuffer.append(JavaClassGenerator.this.TEXT_1083);
											if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
												stringBuffer.append(JavaClassGenerator.this.TEXT_1084);
											} else {
												stringBuffer.append(JavaClassGenerator.this.TEXT_1085);
												stringBuffer.append(genFeature.getCapName());
												stringBuffer.append(JavaClassGenerator.this.TEXT_1086);
											}
											stringBuffer.append(JavaClassGenerator.this.TEXT_1087);
										}
									}
								}
							} else if (genFeature.hasDelegateFeature()) {
								final GenFeature delegateFeature = genFeature.getDelegateFeature();
								if (delegateFeature.isWrappedFeatureMapType()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_1088);
									stringBuffer
									.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
									stringBuffer.append(JavaClassGenerator.this.TEXT_1089);
									stringBuffer
									.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
									stringBuffer.append(JavaClassGenerator.this.TEXT_1090);
									stringBuffer.append(delegateFeature.getAccessorName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_1091);
									stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
									stringBuffer.append(JavaClassGenerator.this.TEXT_1092);
								} else {
									stringBuffer.append(JavaClassGenerator.this.TEXT_1093);
									stringBuffer
									.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
									stringBuffer.append(JavaClassGenerator.this.TEXT_1094);
									stringBuffer.append(delegateFeature.getAccessorName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_1095);
									stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
									stringBuffer.append(JavaClassGenerator.this.TEXT_1096);
								}
							} else if (genClass.getUnsetAccessorOperation(genFeature) != null) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_1097);
								stringBuffer.append(genClass.getUnsetAccessorOperation(genFeature)
										.getBody(genModel.getIndentation(stringBuffer)));
							} else {
								stringBuffer.append(JavaClassGenerator.this.TEXT_1098);
								stringBuffer.append(genFeature.getFormattedName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_1099);
								stringBuffer.append(genFeature.getFeatureKind());
								stringBuffer.append(JavaClassGenerator.this.TEXT_1100);
								// Class/unsetGenFeature.todo.override.javajetinc
							}
							stringBuffer.append(JavaClassGenerator.this.TEXT_1101);
						}
						// Class/unsetGenFeature.override.javajetinc
					}
					if (genFeature.isIsSet() && (isImplementation || !genFeature.isSuppressedIsSetVisibility())) {
						if (isInterface) {
							stringBuffer.append(JavaClassGenerator.this.TEXT_1102);
							stringBuffer.append(genClass.getQualifiedInterfaceName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_1103);
							stringBuffer.append(genFeature.getGetAccessor());
							stringBuffer.append(JavaClassGenerator.this.TEXT_1104);
							stringBuffer.append(genFeature.getFormattedName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_1105);
							stringBuffer.append(genFeature.getFeatureKind());
							stringBuffer.append(JavaClassGenerator.this.TEXT_1106);
							stringBuffer.append(JavaClassGenerator.this.TEXT_1107);
							stringBuffer.append(genFeature.getFormattedName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_1108);
							stringBuffer.append(genFeature.getFeatureKind());
							stringBuffer.append(JavaClassGenerator.this.TEXT_1109);
							if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_1110);
								stringBuffer.append(genFeature.getAccessorName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_1111);
							}
							stringBuffer.append(JavaClassGenerator.this.TEXT_1112);
							stringBuffer.append(genFeature.getGetAccessor());
							stringBuffer.append(JavaClassGenerator.this.TEXT_1113);
							if (!genFeature.isListType() && genFeature.isChangeable()
									&& !genFeature.isSuppressedSetVisibility()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_1114);
								stringBuffer.append(genFeature.getAccessorName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_1115);
								stringBuffer.append(genFeature.getRawImportedBoundType());
								stringBuffer.append(JavaClassGenerator.this.TEXT_1116);
							}
							stringBuffer.append(JavaClassGenerator.this.TEXT_1117);
							// Class/isSetGenFeature.javadoc.override.javajetinc
						} else {
							stringBuffer.append(JavaClassGenerator.this.TEXT_1118);
							if (isJDK50) { // Class/isSetGenFeature.annotations.insert.javajetinc
							}
						}
						if (!isImplementation) {
							stringBuffer.append(JavaClassGenerator.this.TEXT_1119);
							stringBuffer.append(genFeature.getAccessorName());
							stringBuffer.append(JavaClassGenerator.this.TEXT_1120);
						} else {
							stringBuffer.append(JavaClassGenerator.this.TEXT_1121);
							stringBuffer.append(genFeature.getAccessorName());
							if (genClass.hasCollidingIsSetAccessorOperation(genFeature)) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_1122);
							}
							stringBuffer.append(JavaClassGenerator.this.TEXT_1123);
							if (genModel.isDynamicDelegation()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_1124);
								stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
								stringBuffer.append(JavaClassGenerator.this.TEXT_1125);
								stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
								stringBuffer.append(JavaClassGenerator.this.TEXT_1126);
							} else if (genModel.isReflectiveDelegation()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_1127);
								stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
								stringBuffer.append(JavaClassGenerator.this.TEXT_1128);
							} else if (genFeature.hasSettingDelegate()) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_1129);
								stringBuffer.append(genFeature.getUpperName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_1130);
							} else if (!genFeature.isVolatile()) {
								if (genFeature.isListType()) {
									if (genModel.isVirtualDelegation()) {
										stringBuffer.append(JavaClassGenerator.this.TEXT_1131);
										stringBuffer.append(genFeature.getImportedType(genClass));
										stringBuffer.append(JavaClassGenerator.this.TEXT_1132);
										stringBuffer.append(genFeature.getSafeName());
										stringBuffer.append(JavaClassGenerator.this.TEXT_1133);
										stringBuffer.append(genFeature.getImportedType(genClass));
										stringBuffer.append(JavaClassGenerator.this.TEXT_1134);
										stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
										stringBuffer.append(positiveOffsetCorrection);
										stringBuffer.append(JavaClassGenerator.this.TEXT_1135);
									}
									stringBuffer.append(JavaClassGenerator.this.TEXT_1136);
									stringBuffer.append(genFeature.getSafeName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_1137);
									stringBuffer.append(
											genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
									stringBuffer.append(JavaClassGenerator.this.TEXT_1138);
									stringBuffer.append(singleWildcard);
									stringBuffer.append(JavaClassGenerator.this.TEXT_1139);
									stringBuffer.append(genFeature.getSafeName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_1140);
								} else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_1141);
									stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
									stringBuffer.append(positiveOffsetCorrection);
									stringBuffer.append(JavaClassGenerator.this.TEXT_1142);
								} else if (genClass.isESetFlag(genFeature)) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_1143);
									stringBuffer.append(genClass.getESetFlagsField(genFeature));
									stringBuffer.append(JavaClassGenerator.this.TEXT_1144);
									stringBuffer.append(genFeature.getUpperName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_1145);
								} else {
									stringBuffer.append(JavaClassGenerator.this.TEXT_1146);
									stringBuffer.append(genFeature.getUncapName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_1147);
								}
							} else if (genFeature.hasDelegateFeature()) {
								final GenFeature delegateFeature = genFeature.getDelegateFeature();
								if (delegateFeature.isWrappedFeatureMapType()) {
									stringBuffer.append(JavaClassGenerator.this.TEXT_1148);
									stringBuffer
									.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
									stringBuffer.append(JavaClassGenerator.this.TEXT_1149);
									stringBuffer
									.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
									stringBuffer.append(JavaClassGenerator.this.TEXT_1150);
									stringBuffer.append(delegateFeature.getAccessorName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_1151);
									stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
									stringBuffer.append(JavaClassGenerator.this.TEXT_1152);
								} else {
									stringBuffer.append(JavaClassGenerator.this.TEXT_1153);
									stringBuffer
									.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
									stringBuffer.append(JavaClassGenerator.this.TEXT_1154);
									stringBuffer.append(delegateFeature.getAccessorName());
									stringBuffer.append(JavaClassGenerator.this.TEXT_1155);
									stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
									stringBuffer.append(JavaClassGenerator.this.TEXT_1156);
								}
							} else if (genClass.getIsSetAccessorOperation(genFeature) != null) {
								stringBuffer.append(JavaClassGenerator.this.TEXT_1157);
								stringBuffer.append(genClass.getIsSetAccessorOperation(genFeature)
										.getBody(genModel.getIndentation(stringBuffer)));
							} else {
								stringBuffer.append(JavaClassGenerator.this.TEXT_1158);
								stringBuffer.append(genFeature.getFormattedName());
								stringBuffer.append(JavaClassGenerator.this.TEXT_1159);
								stringBuffer.append(genFeature.getFeatureKind());
								stringBuffer.append(JavaClassGenerator.this.TEXT_1160);
								// Class/isSetGenFeature.todo.override.javajetinc
							}
							stringBuffer.append(JavaClassGenerator.this.TEXT_1161);
						}
						// Class/isSetGenFeature.override.javajetinc
					}
					// Class/genFeature.override.javajetinc
				} // for
			}
		}.run();
		for (final GenOperation genOperation : (isImplementation ? genClass.getImplementedGenOperations()
				: genClass.getDeclaredGenOperations())) {
			if (isImplementation) {
				if (genOperation.isInvariant() && genOperation.hasInvariantExpression()) {
					stringBuffer.append(this.TEXT_1162);
					stringBuffer.append(genOperation.getName());
					stringBuffer.append(this.TEXT_1163);
					stringBuffer.append(genOperation.getParameterTypes(", "));
					stringBuffer.append(this.TEXT_1164);
					stringBuffer.append(genOperation.getFormattedName());
					stringBuffer.append(this.TEXT_1165);
					stringBuffer.append(genOperation.getName());
					stringBuffer.append(this.TEXT_1166);
					stringBuffer.append(genOperation.getParameterTypes(", "));
					stringBuffer.append(this.TEXT_1167);
					stringBuffer.append(genModel.getImportedName("java.lang.String"));
					stringBuffer.append(this.TEXT_1168);
					stringBuffer
					.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
					stringBuffer.append(this.TEXT_1169);
					stringBuffer.append(genOperation.getInvariantExpression("\t\t"));
					stringBuffer.append(this.TEXT_1170);
					stringBuffer.append(genModel.getNonNLS());
					stringBuffer.append(this.TEXT_1171);
				} else if (genOperation.hasInvocationDelegate()) {
					stringBuffer.append(this.TEXT_1172);
					stringBuffer.append(genOperation.getName());
					stringBuffer.append(this.TEXT_1173);
					stringBuffer.append(genOperation.getParameterTypes(", "));
					stringBuffer.append(this.TEXT_1174);
					stringBuffer.append(genOperation.getFormattedName());
					stringBuffer.append(this.TEXT_1175);
					stringBuffer.append(genOperation.getName());
					stringBuffer.append(this.TEXT_1176);
					stringBuffer.append(genOperation.getParameterTypes(", "));
					stringBuffer.append(this.TEXT_1177);
					stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
					stringBuffer.append(this.TEXT_1178);
					stringBuffer
					.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
					stringBuffer.append(this.TEXT_1179);
					stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
					stringBuffer.append(this.TEXT_1180);
					stringBuffer.append(genOperation.getQualifiedOperationAccessor());
					stringBuffer.append(this.TEXT_1181);
				}
			}
			if (isInterface) {
				stringBuffer.append(this.TEXT_1182);
				stringBuffer.append(this.TEXT_1183);
				if (genOperation.hasDocumentation() || genOperation.hasParameterDocumentation()) {
					stringBuffer.append(this.TEXT_1184);
					if (genOperation.hasDocumentation()) {
						stringBuffer.append(this.TEXT_1185);
						stringBuffer.append(genOperation.getDocumentation(genModel.getIndentation(stringBuffer)));
					}
					for (final GenParameter genParameter : genOperation.getGenParameters()) {
						if (genParameter.hasDocumentation()) {
							final String documentation = genParameter.getDocumentation("");
							if (documentation.contains("\n") || documentation.contains("\r")) {
								stringBuffer.append(this.TEXT_1186);
								stringBuffer.append(genParameter.getName());
								stringBuffer.append(this.TEXT_1187);
								stringBuffer
								.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
							} else {
								stringBuffer.append(this.TEXT_1188);
								stringBuffer.append(genParameter.getName());
								stringBuffer.append(this.TEXT_1189);
								stringBuffer
								.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
							}
						}
					}
					stringBuffer.append(this.TEXT_1190);
				}
				if (!genModel.isSuppressEMFModelTags()) {
					boolean first = true;
					for (final StringTokenizer stringTokenizer = new StringTokenizer(genOperation.getModelInfo(),
							"\n\r"); stringTokenizer.hasMoreTokens();) {
						final String modelInfo = stringTokenizer.nextToken();
						if (first) {
							first = false;
							stringBuffer.append(this.TEXT_1191);
							stringBuffer.append(modelInfo);
						} else {
							stringBuffer.append(this.TEXT_1192);
							stringBuffer.append(modelInfo);
						}
					}
					if (first) {
						stringBuffer.append(this.TEXT_1193);
					}
				}
				stringBuffer.append(this.TEXT_1194);
				// Class/genOperation.javadoc.override.javajetinc
			} else {
				stringBuffer.append(this.TEXT_1195);
				if (isJDK50) { // Class/genOperation.annotations.insert.javajetinc
				}
			}
			if (!isImplementation) {
				stringBuffer.append(this.TEXT_1196);
				stringBuffer.append(genOperation.getTypeParameters(genClass));
				stringBuffer.append(genOperation.getImportedType(genClass));
				stringBuffer.append(this.TEXT_1197);
				stringBuffer.append(genOperation.getName());
				stringBuffer.append(this.TEXT_1198);
				stringBuffer.append(genOperation.getParameters(genClass));
				stringBuffer.append(this.TEXT_1199);
				stringBuffer.append(genOperation.getThrows(genClass));
				stringBuffer.append(this.TEXT_1200);
			} else {
				if (genModel.useGenerics() && !genOperation.hasBody() && !genOperation.isInvariant()
						&& genOperation.hasInvocationDelegate() && genOperation.isUncheckedCast(genClass)) {
					stringBuffer.append(this.TEXT_1201);
				}
				stringBuffer.append(this.TEXT_1202);
				stringBuffer.append(genOperation.getTypeParameters(genClass));
				stringBuffer.append(genOperation.getImportedType(genClass));
				stringBuffer.append(this.TEXT_1203);
				stringBuffer.append(genOperation.getName());
				stringBuffer.append(this.TEXT_1204);
				stringBuffer.append(genOperation.getParameters(genClass));
				stringBuffer.append(this.TEXT_1205);
				stringBuffer.append(genOperation.getThrows(genClass));
				stringBuffer.append(this.TEXT_1206);
				if (genOperation.hasBody()) {
					stringBuffer.append(this.TEXT_1207);
					stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
				} else if (genOperation.isInvariant()) {
					final GenClass opClass = genOperation.getGenClass();
					final String diagnostics = genOperation.getGenParameters().get(0).getName();
					final String context = genOperation.getGenParameters().get(1).getName();
					if (genOperation.hasInvariantExpression()) {
						stringBuffer.append(this.TEXT_1208);
						stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
						stringBuffer.append(this.TEXT_1209);
						stringBuffer.append(genClass.getQualifiedClassifierAccessor());
						stringBuffer.append(this.TEXT_1210);
						stringBuffer.append(diagnostics);
						stringBuffer.append(this.TEXT_1211);
						stringBuffer.append(context);
						stringBuffer.append(this.TEXT_1212);
						stringBuffer.append(genOperation.getValidationDelegate());
						stringBuffer.append(this.TEXT_1213);
						stringBuffer.append(genModel.getNonNLS());
						stringBuffer.append(this.TEXT_1214);
						stringBuffer.append(genOperation.getQualifiedOperationAccessor());
						stringBuffer.append(this.TEXT_1215);
						stringBuffer.append(
								CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
						stringBuffer.append(this.TEXT_1216);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
						stringBuffer.append(this.TEXT_1217);
						stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
						stringBuffer.append(this.TEXT_1218);
						stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
						stringBuffer.append(this.TEXT_1219);
						stringBuffer.append(opClass.getOperationID(genOperation));
						stringBuffer.append(this.TEXT_1220);
					} else {
						stringBuffer.append(this.TEXT_1221);
						stringBuffer.append(diagnostics);
						stringBuffer.append(this.TEXT_1222);
						stringBuffer.append(diagnostics);
						stringBuffer.append(this.TEXT_1223);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
						stringBuffer.append(this.TEXT_1224);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
						stringBuffer.append(this.TEXT_1225);
						stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
						stringBuffer.append(this.TEXT_1226);
						stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
						stringBuffer.append(this.TEXT_1227);
						stringBuffer.append(opClass.getOperationID(genOperation));
						stringBuffer.append(this.TEXT_1228);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
						stringBuffer.append(this.TEXT_1229);
						stringBuffer.append(genOperation.getName());
						stringBuffer.append(this.TEXT_1230);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
						stringBuffer.append(this.TEXT_1231);
						stringBuffer.append(context);
						stringBuffer.append(this.TEXT_1232);
						stringBuffer.append(genModel.getNonNLS());
						stringBuffer.append(genModel.getNonNLS(2));
						stringBuffer.append(this.TEXT_1233);
					}
				} else if (genOperation.hasInvocationDelegate()) {
					final int size = genOperation.getGenParameters().size();
					stringBuffer.append(this.TEXT_1234);
					if (genOperation.isVoid()) {
						stringBuffer.append(this.TEXT_1235);
						stringBuffer.append(
								CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
						stringBuffer.append(this.TEXT_1236);
						if (size > 0) {
							stringBuffer.append(this.TEXT_1237);
							stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
							stringBuffer.append(this.TEXT_1238);
							stringBuffer.append(size);
							stringBuffer.append(this.TEXT_1239);
							stringBuffer.append(genOperation.getParametersArray(genClass));
							stringBuffer.append(this.TEXT_1240);
						} else {
							stringBuffer.append(this.TEXT_1241);
						}
						stringBuffer.append(this.TEXT_1242);
					} else {
						stringBuffer.append(this.TEXT_1243);
						if (!isJDK50 && genOperation.isPrimitiveType()) {
							stringBuffer.append(this.TEXT_1244);
						}
						stringBuffer.append(this.TEXT_1245);
						stringBuffer.append(genOperation.getObjectType(genClass));
						stringBuffer.append(this.TEXT_1246);
						stringBuffer.append(
								CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
						stringBuffer.append(this.TEXT_1247);
						if (size > 0) {
							stringBuffer.append(this.TEXT_1248);
							stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
							stringBuffer.append(this.TEXT_1249);
							stringBuffer.append(size);
							stringBuffer.append(this.TEXT_1250);
							stringBuffer.append(genOperation.getParametersArray(genClass));
							stringBuffer.append(this.TEXT_1251);
						} else {
							stringBuffer.append(this.TEXT_1252);
						}
						stringBuffer.append(this.TEXT_1253);
						if (!isJDK50 && genOperation.isPrimitiveType()) {
							stringBuffer.append(this.TEXT_1254);
							stringBuffer.append(genOperation.getPrimitiveValueFunction());
							stringBuffer.append(this.TEXT_1255);
						}
						stringBuffer.append(this.TEXT_1256);
					}
					stringBuffer.append(this.TEXT_1257);
					stringBuffer.append(
							genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException"
									: "java.lang.reflect.InvocationTargetException"));
					stringBuffer.append(this.TEXT_1258);
					stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.WrappedException"));
					stringBuffer.append(this.TEXT_1259);
				} else {
					// SDM based method implementation
					final String methodBody = generatorAdapter.getGeneratedMethodBody(genOperation.getEcoreOperation());
					if (methodBody != null) {
						stringBuffer.append(methodBody);
					} else {
						stringBuffer.append(this.TEXT_1260);
					}
					// Class/implementedGenOperation.todo.override.javajetinc
				}
				stringBuffer.append(this.TEXT_1261);
			}
			// Class/implementedGenOperation.override.javajetinc
		} // for
		if (isImplementation && !genModel.isReflectiveDelegation()
				&& genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
			stringBuffer.append(this.TEXT_1262);
			if (genModel.useGenerics()) {
				for (final GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
					if (genFeature.isUncheckedCast(genClass)) {
						stringBuffer.append(this.TEXT_1263);
						break;
					}
				}
			}
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(this.TEXT_1264);
			}
			stringBuffer.append(this.TEXT_1265);
			stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
			stringBuffer.append(this.TEXT_1266);
			stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
			stringBuffer.append(this.TEXT_1267);
			stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
			stringBuffer.append(this.TEXT_1268);
			stringBuffer.append(negativeOffsetCorrection);
			stringBuffer.append(this.TEXT_1269);
			for (final GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
				stringBuffer.append(this.TEXT_1270);
				stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
				stringBuffer.append(this.TEXT_1271);
				if (genFeature.isListType()) {
					final String cast = "(" + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList")
					+ (!genModel.useGenerics() ? ")"
							: "<" + genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject") + ">)("
							+ genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList")
							+ "<?>)");
					if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
						stringBuffer.append(this.TEXT_1272);
						stringBuffer.append(cast);
						stringBuffer.append(this.TEXT_1273);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
						stringBuffer.append(this.TEXT_1274);
						stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
						stringBuffer.append(this.TEXT_1275);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(this.TEXT_1276);
					} else {
						stringBuffer.append(this.TEXT_1277);
						stringBuffer.append(cast);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(this.TEXT_1278);
					}
				} else if (genFeature.isContainer()) {
					stringBuffer.append(this.TEXT_1279);
					if (genFeature.isBasicSet()) {
						stringBuffer.append(this.TEXT_1280);
						stringBuffer.append(genFeature.getAccessorName());
						stringBuffer.append(this.TEXT_1281);
						stringBuffer.append(genFeature.getImportedType(genClass));
						stringBuffer.append(this.TEXT_1282);
					} else {
						stringBuffer.append(this.TEXT_1283);
						stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
						stringBuffer.append(positiveOffsetCorrection);
						stringBuffer.append(this.TEXT_1284);
					}
				} else {
					if (genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
						stringBuffer.append(this.TEXT_1285);
						stringBuffer.append(genFeature.getImportedType(genClass));
						stringBuffer.append(this.TEXT_1286);
						stringBuffer.append(genFeature.getSafeName());
						stringBuffer.append(this.TEXT_1287);
						stringBuffer.append(genFeature.getImportedType(genClass));
						stringBuffer.append(this.TEXT_1288);
						stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
						stringBuffer.append(positiveOffsetCorrection);
						stringBuffer.append(this.TEXT_1289);
					} else if (genFeature.isVolatile()
							|| genClass.getImplementingGenModel(genFeature).isDynamicDelegation()) {
						stringBuffer.append(this.TEXT_1290);
						stringBuffer.append(genFeature.getImportedType(genClass));
						stringBuffer.append(this.TEXT_1291);
						stringBuffer.append(genFeature.getSafeName());
						stringBuffer.append(this.TEXT_1292);
						if (genFeature.isResolveProxies()) {
							stringBuffer.append(this.TEXT_1293);
							stringBuffer.append(genFeature.getAccessorName());
						} else {
							stringBuffer.append(genFeature.getGetAccessor());
						}
						stringBuffer.append(this.TEXT_1294);
					}
					stringBuffer.append(this.TEXT_1295);
					stringBuffer.append(genFeature.getSafeName());
					stringBuffer.append(this.TEXT_1296);
					if (genFeature.isEffectiveContains()) {
						stringBuffer.append(this.TEXT_1297);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
						stringBuffer.append(this.TEXT_1298);
						stringBuffer.append(genFeature.getSafeName());
						stringBuffer.append(this.TEXT_1299);
						stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
						stringBuffer.append(negativeOffsetCorrection);
						stringBuffer.append(this.TEXT_1300);
					} else {
						final GenFeature reverseFeature = genFeature.getReverse();
						final GenClass targetClass = reverseFeature.getGenClass();
						final String reverseOffsetCorrection = targetClass.hasOffsetCorrection()
								? " + " + genClass.getOffsetCorrectionField(genFeature)
								: "";
						stringBuffer.append(this.TEXT_1301);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
						stringBuffer.append(this.TEXT_1302);
						stringBuffer.append(genFeature.getSafeName());
						stringBuffer.append(this.TEXT_1303);
						stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
						stringBuffer.append(reverseOffsetCorrection);
						stringBuffer.append(this.TEXT_1304);
						stringBuffer.append(targetClass.getRawImportedInterfaceName());
						stringBuffer.append(this.TEXT_1305);
					}
					stringBuffer.append(this.TEXT_1306);
					stringBuffer.append(genFeature.getAccessorName());
					stringBuffer.append(this.TEXT_1307);
					stringBuffer.append(genFeature.getImportedType(genClass));
					stringBuffer.append(this.TEXT_1308);
				}
			}
			stringBuffer.append(this.TEXT_1309);
			if (genModel.isMinimalReflectiveMethods()) {
				stringBuffer.append(this.TEXT_1310);
			} else {
				stringBuffer.append(this.TEXT_1311);
			}
			stringBuffer.append(this.TEXT_1312);
		}
		if (isImplementation && !genModel.isReflectiveDelegation()
				&& genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
			stringBuffer.append(this.TEXT_1313);
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(this.TEXT_1314);
			}
			stringBuffer.append(this.TEXT_1315);
			stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
			stringBuffer.append(this.TEXT_1316);
			stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
			stringBuffer.append(this.TEXT_1317);
			stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
			stringBuffer.append(this.TEXT_1318);
			stringBuffer.append(negativeOffsetCorrection);
			stringBuffer.append(this.TEXT_1319);
			for (final GenFeature genFeature : genClass.getEInverseRemoveGenFeatures()) {
				stringBuffer.append(this.TEXT_1320);
				stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
				stringBuffer.append(this.TEXT_1321);
				if (genFeature.isListType()) {
					if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
						stringBuffer.append(this.TEXT_1322);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
						stringBuffer.append(singleWildcard);
						stringBuffer.append(this.TEXT_1323);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
						stringBuffer.append(this.TEXT_1324);
						stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
						stringBuffer.append(this.TEXT_1325);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(this.TEXT_1326);
					} else if (genFeature.isWrappedFeatureMapType()) {
						stringBuffer.append(this.TEXT_1327);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
						stringBuffer.append(singleWildcard);
						stringBuffer.append(this.TEXT_1328);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
						stringBuffer.append(this.TEXT_1329);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(this.TEXT_1330);
					} else {
						stringBuffer.append(this.TEXT_1331);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
						stringBuffer.append(singleWildcard);
						stringBuffer.append(this.TEXT_1332);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(this.TEXT_1333);
					}
				} else if (genFeature.isContainer() && !genFeature.isBasicSet()) {
					stringBuffer.append(this.TEXT_1334);
					stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
					stringBuffer.append(positiveOffsetCorrection);
					stringBuffer.append(this.TEXT_1335);
				} else if (genFeature.isUnsettable()) {
					stringBuffer.append(this.TEXT_1336);
					stringBuffer.append(genFeature.getAccessorName());
					stringBuffer.append(this.TEXT_1337);
				} else {
					stringBuffer.append(this.TEXT_1338);
					stringBuffer.append(genFeature.getAccessorName());
					stringBuffer.append(this.TEXT_1339);
				}
			}
			stringBuffer.append(this.TEXT_1340);
			if (genModel.isMinimalReflectiveMethods()) {
				stringBuffer.append(this.TEXT_1341);
			} else {
				stringBuffer.append(this.TEXT_1342);
			}
			stringBuffer.append(this.TEXT_1343);
		}
		if (isImplementation && !genModel.isReflectiveDelegation()
				&& genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
			stringBuffer.append(this.TEXT_1344);
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(this.TEXT_1345);
			}
			stringBuffer.append(this.TEXT_1346);
			stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
			stringBuffer.append(this.TEXT_1347);
			stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
			stringBuffer.append(this.TEXT_1348);
			stringBuffer.append(negativeOffsetCorrection);
			stringBuffer.append(this.TEXT_1349);
			for (final GenFeature genFeature : genClass.getEBasicRemoveFromContainerGenFeatures()) {
				final GenFeature reverseFeature = genFeature.getReverse();
				final GenClass targetClass = reverseFeature.getGenClass();
				final String reverseOffsetCorrection = targetClass.hasOffsetCorrection()
						? " + " + genClass.getOffsetCorrectionField(genFeature)
						: "";
				stringBuffer.append(this.TEXT_1350);
				stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
				stringBuffer.append(this.TEXT_1351);
				stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
				stringBuffer.append(reverseOffsetCorrection);
				stringBuffer.append(this.TEXT_1352);
				stringBuffer.append(targetClass.getRawImportedInterfaceName());
				stringBuffer.append(this.TEXT_1353);
			}
			stringBuffer.append(this.TEXT_1354);
			if (genModel.isMinimalReflectiveMethods()) {
				stringBuffer.append(this.TEXT_1355);
			} else {
				stringBuffer.append(this.TEXT_1356);
			}
			stringBuffer.append(this.TEXT_1357);
		}
		if (isImplementation && !genModel.isReflectiveDelegation()
				&& genClass.implementsAny(genClass.getEGetGenFeatures())) {
			stringBuffer.append(this.TEXT_1358);
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(this.TEXT_1359);
			}
			stringBuffer.append(this.TEXT_1360);
			stringBuffer.append(negativeOffsetCorrection);
			stringBuffer.append(this.TEXT_1361);
			for (final GenFeature genFeature : genClass.getEGetGenFeatures()) {
				stringBuffer.append(this.TEXT_1362);
				stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
				stringBuffer.append(this.TEXT_1363);
				if (genFeature.isPrimitiveType()) {
					if (isJDK50) {
						stringBuffer.append(this.TEXT_1364);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(this.TEXT_1365);
					} else if (genFeature.isBooleanType()) {
						stringBuffer.append(this.TEXT_1366);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(this.TEXT_1367);
					} else {
						stringBuffer.append(this.TEXT_1368);
						stringBuffer.append(genFeature.getObjectType(genClass));
						stringBuffer.append(this.TEXT_1369);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(this.TEXT_1370);
					}
				} else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
					stringBuffer.append(this.TEXT_1371);
					stringBuffer.append(genFeature.getGetAccessor());
					stringBuffer.append(this.TEXT_1372);
					stringBuffer.append(genFeature.getAccessorName());
					stringBuffer.append(this.TEXT_1373);
				} else if (genFeature.isMapType()) {
					if (genFeature.isEffectiveSuppressEMFTypes()) {
						stringBuffer.append(this.TEXT_1374);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
						stringBuffer.append(this.TEXT_1375);
						stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
						stringBuffer.append(this.TEXT_1376);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(this.TEXT_1377);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(this.TEXT_1378);
					} else {
						stringBuffer.append(this.TEXT_1379);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(this.TEXT_1380);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(this.TEXT_1381);
					}
				} else if (genFeature.isWrappedFeatureMapType()) {
					stringBuffer.append(this.TEXT_1382);
					stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
					stringBuffer.append(this.TEXT_1383);
					stringBuffer.append(genFeature.getGetAccessor());
					stringBuffer.append(this.TEXT_1384);
					stringBuffer.append(genFeature.getGetAccessor());
					stringBuffer.append(this.TEXT_1385);
				} else if (genFeature.isFeatureMapType()) {
					stringBuffer.append(this.TEXT_1386);
					stringBuffer.append(genFeature.getGetAccessor());
					stringBuffer.append(this.TEXT_1387);
					stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
					stringBuffer.append(this.TEXT_1388);
					stringBuffer.append(genFeature.getGetAccessor());
					stringBuffer.append(this.TEXT_1389);
				} else {
					stringBuffer.append(this.TEXT_1390);
					stringBuffer.append(genFeature.getGetAccessor());
					stringBuffer.append(this.TEXT_1391);
				}
			}
			stringBuffer.append(this.TEXT_1392);
			if (genModel.isMinimalReflectiveMethods()) {
				stringBuffer.append(this.TEXT_1393);
			} else {
				stringBuffer.append(this.TEXT_1394);
			}
			stringBuffer.append(this.TEXT_1395);
		}
		if (isImplementation && !genModel.isReflectiveDelegation()
				&& genClass.implementsAny(genClass.getESetGenFeatures())) {
			stringBuffer.append(this.TEXT_1396);
			if (genModel.useGenerics()) {
				for (final GenFeature genFeature : genClass.getESetGenFeatures()) {
					if (genFeature.isUncheckedCast(genClass) && !genFeature.isFeatureMapType()
							&& !genFeature.isMapType()) {
						stringBuffer.append(this.TEXT_1397);
						break;
					}
				}
			}
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(this.TEXT_1398);
			}
			stringBuffer.append(this.TEXT_1399);
			stringBuffer.append(negativeOffsetCorrection);
			stringBuffer.append(this.TEXT_1400);
			for (final GenFeature genFeature : genClass.getESetGenFeatures()) {
				stringBuffer.append(this.TEXT_1401);
				stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
				stringBuffer.append(this.TEXT_1402);
				if (genFeature.isListType()) {
					if (genFeature.isWrappedFeatureMapType()) {
						stringBuffer.append(this.TEXT_1403);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
						stringBuffer.append(this.TEXT_1404);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
						stringBuffer.append(this.TEXT_1405);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(this.TEXT_1406);
					} else if (genFeature.isFeatureMapType()) {
						stringBuffer.append(this.TEXT_1407);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
						stringBuffer.append(this.TEXT_1408);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(this.TEXT_1409);
					} else if (genFeature.isMapType()) {
						if (genFeature.isEffectiveSuppressEMFTypes()) {
							stringBuffer.append(this.TEXT_1410);
							stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
							stringBuffer.append(this.TEXT_1411);
							stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
							stringBuffer.append(this.TEXT_1412);
							stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
							stringBuffer.append(this.TEXT_1413);
							stringBuffer.append(genFeature.getGetAccessor());
							stringBuffer.append(this.TEXT_1414);
						} else {
							stringBuffer.append(this.TEXT_1415);
							stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
							stringBuffer.append(this.TEXT_1416);
							stringBuffer.append(genFeature.getGetAccessor());
							stringBuffer.append(this.TEXT_1417);
						}
					} else {
						stringBuffer.append(this.TEXT_1418);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(this.TEXT_1419);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(this.TEXT_1420);
						stringBuffer.append(genModel.getImportedName("java.util.Collection"));
						if (isJDK50) {
							stringBuffer.append(this.TEXT_1421);
							stringBuffer.append(genFeature.getListItemType(genClass));
							stringBuffer.append(this.TEXT_1422);
						}
						stringBuffer.append(this.TEXT_1423);
					}
				} else if (!isJDK50 && genFeature.isPrimitiveType()) {
					stringBuffer.append(this.TEXT_1424);
					stringBuffer.append(genFeature.getAccessorName());
					stringBuffer.append(this.TEXT_1425);
					stringBuffer.append(genFeature.getObjectType(genClass));
					stringBuffer.append(this.TEXT_1426);
					stringBuffer.append(genFeature.getPrimitiveValueFunction());
					stringBuffer.append(this.TEXT_1427);
				} else {
					stringBuffer.append(this.TEXT_1428);
					stringBuffer.append(genFeature.getAccessorName());
					stringBuffer.append(this.TEXT_1429);
					if ((genFeature.getTypeGenDataType() == null) || !genFeature.getTypeGenDataType().isObjectType()
							|| !genFeature.getRawType().equals(genFeature.getType(genClass))) {
						stringBuffer.append(this.TEXT_1430);
						stringBuffer.append(genFeature.getObjectType(genClass));
						stringBuffer.append(this.TEXT_1431);
					}
					stringBuffer.append(this.TEXT_1432);
				}
				stringBuffer.append(this.TEXT_1433);
			}
			stringBuffer.append(this.TEXT_1434);
			if (genModel.isMinimalReflectiveMethods()) {
				stringBuffer.append(this.TEXT_1435);
			} else {
				stringBuffer.append(this.TEXT_1436);
			}
			stringBuffer.append(this.TEXT_1437);
		}
		if (isImplementation && !genModel.isReflectiveDelegation()
				&& genClass.implementsAny(genClass.getEUnsetGenFeatures())) {
			stringBuffer.append(this.TEXT_1438);
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(this.TEXT_1439);
			}
			stringBuffer.append(this.TEXT_1440);
			stringBuffer.append(negativeOffsetCorrection);
			stringBuffer.append(this.TEXT_1441);
			for (final GenFeature genFeature : genClass.getEUnsetGenFeatures()) {
				stringBuffer.append(this.TEXT_1442);
				stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
				stringBuffer.append(this.TEXT_1443);
				if (genFeature.isListType() && !genFeature.isUnsettable()) {
					if (genFeature.isWrappedFeatureMapType()) {
						stringBuffer.append(this.TEXT_1444);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
						stringBuffer.append(this.TEXT_1445);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(this.TEXT_1446);
					} else {
						stringBuffer.append(this.TEXT_1447);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(this.TEXT_1448);
					}
				} else if (genFeature.isUnsettable()) {
					stringBuffer.append(this.TEXT_1449);
					stringBuffer.append(genFeature.getAccessorName());
					stringBuffer.append(this.TEXT_1450);
				} else if (!genFeature.hasEDefault()) {
					stringBuffer.append(this.TEXT_1451);
					stringBuffer.append(genFeature.getAccessorName());
					stringBuffer.append(this.TEXT_1452);
					stringBuffer.append(genFeature.getImportedType(genClass));
					stringBuffer.append(this.TEXT_1453);
				} else {
					stringBuffer.append(this.TEXT_1454);
					stringBuffer.append(genFeature.getAccessorName());
					stringBuffer.append(this.TEXT_1455);
					stringBuffer.append(genFeature.getEDefault());
					stringBuffer.append(this.TEXT_1456);
				}
				stringBuffer.append(this.TEXT_1457);
			}
			stringBuffer.append(this.TEXT_1458);
			if (genModel.isMinimalReflectiveMethods()) {
				stringBuffer.append(this.TEXT_1459);
			} else {
				stringBuffer.append(this.TEXT_1460);
			}
			stringBuffer.append(this.TEXT_1461);
			// Class/eUnset.override.javajetinc
		}
		if (isImplementation && !genModel.isReflectiveDelegation()
				&& genClass.implementsAny(genClass.getEIsSetGenFeatures())) {
			stringBuffer.append(this.TEXT_1462);
			if (genModel.useGenerics()) {
				for (final GenFeature genFeature : genClass.getEIsSetGenFeatures()) {
					if (genFeature.isListType() && !genFeature.isUnsettable() && !genFeature.isWrappedFeatureMapType()
							&& !genClass.isField(genFeature) && genFeature.isField()
							&& genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
						stringBuffer.append(this.TEXT_1463);
						break;
					}
				}
			}
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(this.TEXT_1464);
			}
			stringBuffer.append(this.TEXT_1465);
			stringBuffer.append(negativeOffsetCorrection);
			stringBuffer.append(this.TEXT_1466);
			for (final GenFeature genFeature : genClass.getEIsSetGenFeatures()) {
				String safeNameAccessor = genFeature.getSafeName();
				if ("featureID".equals(safeNameAccessor)) {
					safeNameAccessor = "this." + safeNameAccessor;
				}
				stringBuffer.append(this.TEXT_1467);
				stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
				stringBuffer.append(this.TEXT_1468);
				if (genFeature.hasSettingDelegate()) {
					if (genFeature.isUnsettable()) {
						stringBuffer.append(this.TEXT_1469);
						stringBuffer.append(genFeature.getAccessorName());
						stringBuffer.append(this.TEXT_1470);
					} else {
						stringBuffer.append(this.TEXT_1471);
						stringBuffer.append(genFeature.getUpperName());
						stringBuffer.append(this.TEXT_1472);
					}
				} else if (genFeature.isListType() && !genFeature.isUnsettable()) {
					if (genFeature.isWrappedFeatureMapType()) {
						if (genFeature.isVolatile()) {
							stringBuffer.append(this.TEXT_1473);
							stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
							stringBuffer.append(this.TEXT_1474);
							stringBuffer.append(genFeature.getGetAccessor());
							stringBuffer.append(this.TEXT_1475);
						} else {
							stringBuffer.append(this.TEXT_1476);
							stringBuffer.append(safeNameAccessor);
							stringBuffer.append(this.TEXT_1477);
							stringBuffer.append(safeNameAccessor);
							stringBuffer.append(this.TEXT_1478);
						}
					} else if (genClass.isField(genFeature)) {
						stringBuffer.append(this.TEXT_1479);
						stringBuffer.append(safeNameAccessor);
						stringBuffer.append(this.TEXT_1480);
						stringBuffer.append(safeNameAccessor);
						stringBuffer.append(this.TEXT_1481);
					} else if (genFeature.isField()
							&& genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
						stringBuffer.append(this.TEXT_1482);
						stringBuffer.append(genFeature.getImportedType(genClass));
						stringBuffer.append(this.TEXT_1483);
						stringBuffer.append(safeNameAccessor);
						stringBuffer.append(this.TEXT_1484);
						stringBuffer.append(genFeature.getImportedType(genClass));
						stringBuffer.append(this.TEXT_1485);
						stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
						stringBuffer.append(positiveOffsetCorrection);
						stringBuffer.append(this.TEXT_1486);
						stringBuffer.append(safeNameAccessor);
						stringBuffer.append(this.TEXT_1487);
						stringBuffer.append(safeNameAccessor);
						stringBuffer.append(this.TEXT_1488);
					} else {
						stringBuffer.append(this.TEXT_1489);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(this.TEXT_1490);
					}
				} else if (genFeature.isUnsettable()) {
					stringBuffer.append(this.TEXT_1491);
					stringBuffer.append(genFeature.getAccessorName());
					stringBuffer.append(this.TEXT_1492);
				} else if (genFeature.isResolveProxies()) {
					if (genClass.isField(genFeature)) {
						stringBuffer.append(this.TEXT_1493);
						stringBuffer.append(safeNameAccessor);
						stringBuffer.append(this.TEXT_1494);
					} else if (genFeature.isField()
							&& genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
						stringBuffer.append(this.TEXT_1495);
						stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
						stringBuffer.append(positiveOffsetCorrection);
						stringBuffer.append(this.TEXT_1496);
					} else {
						stringBuffer.append(this.TEXT_1497);
						stringBuffer.append(genFeature.getAccessorName());
						stringBuffer.append(this.TEXT_1498);
					}
				} else if (!genFeature.hasEDefault()) {
					if (genClass.isField(genFeature)) {
						stringBuffer.append(this.TEXT_1499);
						stringBuffer.append(safeNameAccessor);
						stringBuffer.append(this.TEXT_1500);
					} else if (genFeature.isField()
							&& genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
						stringBuffer.append(this.TEXT_1501);
						stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
						stringBuffer.append(positiveOffsetCorrection);
						stringBuffer.append(this.TEXT_1502);
					} else {
						stringBuffer.append(this.TEXT_1503);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(this.TEXT_1504);
					}
				} else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
					if (genClass.isField(genFeature)) {
						if (genClass.isFlag(genFeature)) {
							if (genFeature.isBooleanType()) {
								stringBuffer.append(this.TEXT_1505);
								stringBuffer.append(genClass.getFlagsField(genFeature));
								stringBuffer.append(this.TEXT_1506);
								stringBuffer.append(genFeature.getUpperName());
								stringBuffer.append(this.TEXT_1507);
								stringBuffer.append(genFeature.getEDefault());
								stringBuffer.append(this.TEXT_1508);
							} else {
								stringBuffer.append(this.TEXT_1509);
								stringBuffer.append(genClass.getFlagsField(genFeature));
								stringBuffer.append(this.TEXT_1510);
								stringBuffer.append(genFeature.getUpperName());
								stringBuffer.append(this.TEXT_1511);
								stringBuffer.append(genFeature.getUpperName());
								stringBuffer.append(this.TEXT_1512);
							}
						} else {
							stringBuffer.append(this.TEXT_1513);
							stringBuffer.append(safeNameAccessor);
							stringBuffer.append(this.TEXT_1514);
							stringBuffer.append(genFeature.getEDefault());
							stringBuffer.append(this.TEXT_1515);
						}
					} else if (genFeature.isEnumType() && genFeature.isField()
							&& genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
						stringBuffer.append(this.TEXT_1516);
						stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
						stringBuffer.append(positiveOffsetCorrection);
						stringBuffer.append(this.TEXT_1517);
						stringBuffer.append(genFeature.getEDefault());
						stringBuffer.append(this.TEXT_1518);
						stringBuffer.append(genFeature.getEDefault());
						stringBuffer.append(this.TEXT_1519);
					} else {
						stringBuffer.append(this.TEXT_1520);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(this.TEXT_1521);
						stringBuffer.append(genFeature.getEDefault());
						stringBuffer.append(this.TEXT_1522);
					}
				} else if (genClass.isField(genFeature)) {
					stringBuffer.append(this.TEXT_1523);
					stringBuffer.append(genFeature.getEDefault());
					stringBuffer.append(this.TEXT_1524);
					stringBuffer.append(safeNameAccessor);
					stringBuffer.append(this.TEXT_1525);
					stringBuffer.append(genFeature.getEDefault());
					stringBuffer.append(this.TEXT_1526);
					stringBuffer.append(safeNameAccessor);
					stringBuffer.append(this.TEXT_1527);
				} else if (genFeature.isField()
						&& genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
					stringBuffer.append(this.TEXT_1528);
					stringBuffer.append(genFeature.getImportedType(genClass));
					stringBuffer.append(this.TEXT_1529);
					stringBuffer.append(safeNameAccessor);
					stringBuffer.append(this.TEXT_1530);
					stringBuffer.append(genFeature.getImportedType(genClass));
					stringBuffer.append(this.TEXT_1531);
					stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
					stringBuffer.append(positiveOffsetCorrection);
					stringBuffer.append(this.TEXT_1532);
					stringBuffer.append(genFeature.getEDefault());
					stringBuffer.append(this.TEXT_1533);
					stringBuffer.append(genFeature.getEDefault());
					stringBuffer.append(this.TEXT_1534);
					stringBuffer.append(safeNameAccessor);
					stringBuffer.append(this.TEXT_1535);
					stringBuffer.append(genFeature.getEDefault());
					stringBuffer.append(this.TEXT_1536);
					stringBuffer.append(safeNameAccessor);
					stringBuffer.append(this.TEXT_1537);
				} else {
					stringBuffer.append(this.TEXT_1538);
					stringBuffer.append(genFeature.getEDefault());
					stringBuffer.append(this.TEXT_1539);
					stringBuffer.append(genFeature.getGetAccessor());
					stringBuffer.append(this.TEXT_1540);
					stringBuffer.append(genFeature.getEDefault());
					stringBuffer.append(this.TEXT_1541);
					stringBuffer.append(genFeature.getGetAccessor());
					stringBuffer.append(this.TEXT_1542);
				}
			}
			stringBuffer.append(this.TEXT_1543);
			if (genModel.isMinimalReflectiveMethods()) {
				stringBuffer.append(this.TEXT_1544);
			} else {
				stringBuffer.append(this.TEXT_1545);
			}
			stringBuffer.append(this.TEXT_1546);
			// Class/eIsSet.override.javajetinc
		}
		if (isImplementation && (!genClass.getMixinGenFeatures().isEmpty()
				|| (genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty()))) {
			if (!genClass.getMixinGenFeatures().isEmpty()) {
				stringBuffer.append(this.TEXT_1547);
				if (genModel.useClassOverrideAnnotation()) {
					stringBuffer.append(this.TEXT_1548);
				}
				stringBuffer.append(this.TEXT_1549);
				stringBuffer.append(singleWildcard);
				stringBuffer.append(this.TEXT_1550);
				for (final GenClass mixinGenClass : genClass.getMixinGenClasses()) {
					stringBuffer.append(this.TEXT_1551);
					stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
					stringBuffer.append(this.TEXT_1552);
					stringBuffer.append(negativeOffsetCorrection);
					stringBuffer.append(this.TEXT_1553);
					for (final GenFeature genFeature : mixinGenClass.getGenFeatures()) {
						stringBuffer.append(this.TEXT_1554);
						stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
						stringBuffer.append(this.TEXT_1555);
						stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
						stringBuffer.append(this.TEXT_1556);
					}
					stringBuffer.append(this.TEXT_1557);
				}
				stringBuffer.append(this.TEXT_1558);
			}
			stringBuffer.append(this.TEXT_1559);
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(this.TEXT_1560);
			}
			stringBuffer.append(this.TEXT_1561);
			stringBuffer.append(singleWildcard);
			stringBuffer.append(this.TEXT_1562);
			for (final GenClass mixinGenClass : genClass.getMixinGenClasses()) {
				stringBuffer.append(this.TEXT_1563);
				stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
				stringBuffer.append(this.TEXT_1564);
				for (final GenFeature genFeature : mixinGenClass.getGenFeatures()) {
					stringBuffer.append(this.TEXT_1565);
					stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
					stringBuffer.append(this.TEXT_1566);
					stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
					stringBuffer.append(positiveOffsetCorrection);
					stringBuffer.append(this.TEXT_1567);
				}
				stringBuffer.append(this.TEXT_1568);
			}
			if (genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty()) {
				stringBuffer.append(this.TEXT_1569);
				stringBuffer.append(genClass.getRawImportedInterfaceName());
				stringBuffer.append(this.TEXT_1570);
				stringBuffer.append(negativeOffsetCorrection);
				stringBuffer.append(this.TEXT_1571);
				for (final GenFeature genFeature : genClass.getGenFeatures()) {
					stringBuffer.append(this.TEXT_1572);
					stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
					stringBuffer.append(this.TEXT_1573);
					stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
					stringBuffer.append(positiveOffsetCorrection);
					stringBuffer.append(this.TEXT_1574);
				}
				stringBuffer.append(this.TEXT_1575);
			}
			stringBuffer.append(this.TEXT_1576);
		}
		if (genModel.isOperationReflection() && isImplementation
				&& (!genClass.getMixinGenOperations().isEmpty()
						|| !genClass.getOverrideGenOperations(genClass.getExtendedGenOperations(),
								genClass.getImplementedGenOperations()).isEmpty()
						|| (genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty()))) {
			stringBuffer.append(this.TEXT_1577);
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(this.TEXT_1578);
			}
			stringBuffer.append(this.TEXT_1579);
			stringBuffer.append(singleWildcard);
			stringBuffer.append(this.TEXT_1580);
			for (final GenClass extendedGenClass : genClass.getExtendedGenClasses()) {
				final List<GenOperation> extendedImplementedGenOperations = extendedGenClass.getImplementedGenOperations();
				final List<GenOperation> implementedGenOperations = genClass.getImplementedGenOperations();
				if (!genClass.getOverrideGenOperations(extendedImplementedGenOperations, implementedGenOperations)
						.isEmpty()) {
					stringBuffer.append(this.TEXT_1581);
					stringBuffer.append(extendedGenClass.getRawImportedInterfaceName());
					stringBuffer.append(this.TEXT_1582);
					for (final GenOperation genOperation : extendedImplementedGenOperations) {
						final GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
						if (implementedGenOperations.contains(overrideGenOperation)) {
							stringBuffer.append(this.TEXT_1583);
							stringBuffer.append(extendedGenClass.getQualifiedOperationID(genOperation));
							stringBuffer.append(this.TEXT_1584);
							stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation));
							stringBuffer.append(positiveOperationOffsetCorrection);
							stringBuffer.append(this.TEXT_1585);
						}
					}
					stringBuffer.append(this.TEXT_1586);
				}
			}
			for (final GenClass mixinGenClass : genClass.getMixinGenClasses()) {
				stringBuffer.append(this.TEXT_1587);
				stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
				stringBuffer.append(this.TEXT_1588);
				for (final GenOperation genOperation : mixinGenClass.getGenOperations()) {
					final GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
					stringBuffer.append(this.TEXT_1589);
					stringBuffer.append(mixinGenClass.getQualifiedOperationID(genOperation));
					stringBuffer.append(this.TEXT_1590);
					stringBuffer.append(genClass.getQualifiedOperationID(
							overrideGenOperation != null ? overrideGenOperation : genOperation));
					stringBuffer.append(positiveOperationOffsetCorrection);
					stringBuffer.append(this.TEXT_1591);
				}
				stringBuffer.append(this.TEXT_1592);
			}
			if (genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty()) {
				stringBuffer.append(this.TEXT_1593);
				stringBuffer.append(genClass.getRawImportedInterfaceName());
				stringBuffer.append(this.TEXT_1594);
				stringBuffer.append(negativeOperationOffsetCorrection);
				stringBuffer.append(this.TEXT_1595);
				for (final GenOperation genOperation : genClass.getGenOperations()) {
					stringBuffer.append(this.TEXT_1596);
					stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
					stringBuffer.append(this.TEXT_1597);
					stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
					stringBuffer.append(positiveOperationOffsetCorrection);
					stringBuffer.append(this.TEXT_1598);
				}
				stringBuffer.append(this.TEXT_1599);
			}
			stringBuffer.append(this.TEXT_1600);
		}
		if (isImplementation && genModel.isVirtualDelegation()) {
			final String eVirtualValuesField = genClass.getEVirtualValuesField();
			if (eVirtualValuesField != null) {
				stringBuffer.append(this.TEXT_1601);
				if (genModel.useClassOverrideAnnotation()) {
					stringBuffer.append(this.TEXT_1602);
				}
				stringBuffer.append(this.TEXT_1603);
				stringBuffer.append(eVirtualValuesField);
				stringBuffer.append(this.TEXT_1604);
				if (genModel.useClassOverrideAnnotation()) {
					stringBuffer.append(this.TEXT_1605);
				}
				stringBuffer.append(this.TEXT_1606);
				stringBuffer.append(eVirtualValuesField);
				stringBuffer.append(this.TEXT_1607);
			}
			{
				final List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
				if (!eVirtualIndexBitFields.isEmpty()) {
					final List<String> allEVirtualIndexBitFields = genClass
							.getAllEVirtualIndexBitFields(new ArrayList<String>());
					stringBuffer.append(this.TEXT_1608);
					if (genModel.useClassOverrideAnnotation()) {
						stringBuffer.append(this.TEXT_1609);
					}
					stringBuffer.append(this.TEXT_1610);
					for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
						stringBuffer.append(this.TEXT_1611);
						stringBuffer.append(i);
						stringBuffer.append(this.TEXT_1612);
						stringBuffer.append(allEVirtualIndexBitFields.get(i));
						stringBuffer.append(this.TEXT_1613);
					}
					stringBuffer.append(this.TEXT_1614);
					if (genModel.useClassOverrideAnnotation()) {
						stringBuffer.append(this.TEXT_1615);
					}
					stringBuffer.append(this.TEXT_1616);
					for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
						stringBuffer.append(this.TEXT_1617);
						stringBuffer.append(i);
						stringBuffer.append(this.TEXT_1618);
						stringBuffer.append(allEVirtualIndexBitFields.get(i));
						stringBuffer.append(this.TEXT_1619);
					}
					stringBuffer.append(this.TEXT_1620);
				}
			}
		}
		if (genModel.isOperationReflection() && isImplementation && !genClass.getImplementedGenOperations().isEmpty()) {
			stringBuffer.append(this.TEXT_1621);
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(this.TEXT_1622);
			}
			if (genModel.useGenerics()) {
				boolean isUnchecked = false;
				boolean isRaw = false;
				LOOP: for (final GenOperation genOperation : (genModel.isMinimalReflectiveMethods()
						? genClass.getImplementedGenOperations()
								: genClass.getAllGenOperations())) {
					for (final GenParameter genParameter : genOperation.getGenParameters()) {
						if (genParameter.isUncheckedCast()) {
							if ((genParameter.getTypeGenDataType() == null)
									|| !genParameter.getTypeGenDataType().isObjectType()) {
								isUnchecked = true;
							}
							if (genParameter.usesOperationTypeParameters() && !genParameter.getEcoreParameter()
									.getEGenericType().getETypeArguments().isEmpty()) {
								isRaw = true;
								break LOOP;
							}
						}
					}
				}
				if (isUnchecked) {
					stringBuffer.append(this.TEXT_1623);
					if (!isRaw) {
						stringBuffer.append(this.TEXT_1624);
					} else {
						stringBuffer.append(this.TEXT_1625);
					}
					stringBuffer.append(this.TEXT_1626);
				}
			}
			stringBuffer.append(this.TEXT_1627);
			stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EList"));
			stringBuffer.append(singleWildcard);
			stringBuffer.append(this.TEXT_1628);
			stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException"
					: "java.lang.reflect.InvocationTargetException"));
			stringBuffer.append(this.TEXT_1629);
			stringBuffer.append(negativeOperationOffsetCorrection);
			stringBuffer.append(this.TEXT_1630);
			for (final GenOperation genOperation : (genModel.isMinimalReflectiveMethods()
					? genClass.getImplementedGenOperations()
							: genClass.getAllGenOperations())) {
				final List<GenParameter> genParameters = genOperation.getGenParameters();
				final int size = genParameters.size();
				stringBuffer.append(this.TEXT_1631);
				stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
				stringBuffer.append(this.TEXT_1632);
				if (genOperation.isVoid()) {
					stringBuffer.append(this.TEXT_1633);
					stringBuffer.append(genOperation.getName());
					stringBuffer.append(this.TEXT_1634);
					for (int i = 0; i < size; i++) {
						final GenParameter genParameter = genParameters.get(i);
						if (!isJDK50 && genParameter.isPrimitiveType()) {
							stringBuffer.append(this.TEXT_1635);
						}
						if ((genParameter.getTypeGenDataType() == null)
								|| !genParameter.getTypeGenDataType().isObjectType()
								|| (!genParameter.usesOperationTypeParameters()
										&& !genParameter.getRawType().equals(genParameter.getType(genClass)))) {
							stringBuffer.append(this.TEXT_1636);
							stringBuffer.append(
									genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType()
											: genParameter.getObjectType(genClass));
							stringBuffer.append(this.TEXT_1637);
						}
						stringBuffer.append(this.TEXT_1638);
						stringBuffer.append(i);
						stringBuffer.append(this.TEXT_1639);
						if (!isJDK50 && genParameter.isPrimitiveType()) {
							stringBuffer.append(this.TEXT_1640);
							stringBuffer.append(genParameter.getPrimitiveValueFunction());
							stringBuffer.append(this.TEXT_1641);
						}
						if (i < (size - 1)) {
							stringBuffer.append(this.TEXT_1642);
						}
					}
					stringBuffer.append(this.TEXT_1643);
				} else {
					stringBuffer.append(this.TEXT_1644);
					if (!isJDK50 && genOperation.isPrimitiveType()) {
						stringBuffer.append(this.TEXT_1645);
						stringBuffer.append(genOperation.getObjectType(genClass));
						stringBuffer.append(this.TEXT_1646);
					}
					stringBuffer.append(genOperation.getName());
					stringBuffer.append(this.TEXT_1647);
					for (int i = 0; i < size; i++) {
						final GenParameter genParameter = genParameters.get(i);
						if (!isJDK50 && genParameter.isPrimitiveType()) {
							stringBuffer.append(this.TEXT_1648);
						}
						if ((genParameter.getTypeGenDataType() == null)
								|| !genParameter.getTypeGenDataType().isObjectType()
								|| (!genParameter.usesOperationTypeParameters()
										&& !genParameter.getRawType().equals(genParameter.getType(genClass)))) {
							stringBuffer.append(this.TEXT_1649);
							stringBuffer.append(
									genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType()
											: genParameter.getObjectType(genClass));
							stringBuffer.append(this.TEXT_1650);
						}
						stringBuffer.append(this.TEXT_1651);
						stringBuffer.append(i);
						stringBuffer.append(this.TEXT_1652);
						if (!isJDK50 && genParameter.isPrimitiveType()) {
							stringBuffer.append(this.TEXT_1653);
							stringBuffer.append(genParameter.getPrimitiveValueFunction());
							stringBuffer.append(this.TEXT_1654);
						}
						if (i < (size - 1)) {
							stringBuffer.append(this.TEXT_1655);
						}
					}
					stringBuffer.append(this.TEXT_1656);
					if (!isJDK50 && genOperation.isPrimitiveType()) {
						stringBuffer.append(this.TEXT_1657);
					}
					stringBuffer.append(this.TEXT_1658);
				}
			}
			stringBuffer.append(this.TEXT_1659);
			if (genModel.isMinimalReflectiveMethods()) {
				stringBuffer.append(this.TEXT_1660);
			} else {
				stringBuffer.append(this.TEXT_1661);
			}
			stringBuffer.append(this.TEXT_1662);
		}
		if (!genClass.hasImplementedToStringGenOperation() && isImplementation && !genModel.isReflectiveDelegation()
				&& !genModel.isDynamicDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
			stringBuffer.append(this.TEXT_1663);
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(this.TEXT_1664);
			}
			stringBuffer.append(this.TEXT_1665);
			{
				boolean first = true;
				for (final GenFeature genFeature : genClass.getToStringGenFeatures()) {
					if (first) {
						first = false;
						stringBuffer.append(this.TEXT_1666);
						stringBuffer.append(genFeature.getName());
						stringBuffer.append(this.TEXT_1667);
						stringBuffer.append(genModel.getNonNLS());
					} else {
						stringBuffer.append(this.TEXT_1668);
						stringBuffer.append(genFeature.getName());
						stringBuffer.append(this.TEXT_1669);
						stringBuffer.append(genModel.getNonNLS());
					}
					if (genFeature.isUnsettable() && !genFeature.isListType()) {
						if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
							stringBuffer.append(this.TEXT_1670);
							stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
							stringBuffer.append(positiveOffsetCorrection);
							stringBuffer.append(this.TEXT_1671);
							stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
							stringBuffer.append(positiveOffsetCorrection);
							stringBuffer.append(this.TEXT_1672);
							stringBuffer.append(genModel.getNonNLS());
						} else if (genClass.isFlag(genFeature)) {
							if (genFeature.isBooleanType()) {
								stringBuffer.append(this.TEXT_1673);
								if (genClass.isESetFlag(genFeature)) {
									stringBuffer.append(this.TEXT_1674);
									stringBuffer.append(genClass.getESetFlagsField(genFeature));
									stringBuffer.append(this.TEXT_1675);
									stringBuffer.append(genFeature.getUpperName());
									stringBuffer.append(this.TEXT_1676);
								} else {
									stringBuffer.append(genFeature.getUncapName());
									stringBuffer.append(this.TEXT_1677);
								}
								stringBuffer.append(this.TEXT_1678);
								stringBuffer.append(genClass.getFlagsField(genFeature));
								stringBuffer.append(this.TEXT_1679);
								stringBuffer.append(genFeature.getUpperName());
								stringBuffer.append(this.TEXT_1680);
								stringBuffer.append(genModel.getNonNLS());
							} else {
								stringBuffer.append(this.TEXT_1681);
								if (genClass.isESetFlag(genFeature)) {
									stringBuffer.append(this.TEXT_1682);
									stringBuffer.append(genClass.getESetFlagsField(genFeature));
									stringBuffer.append(this.TEXT_1683);
									stringBuffer.append(genFeature.getUpperName());
									stringBuffer.append(this.TEXT_1684);
								} else {
									stringBuffer.append(genFeature.getUncapName());
									stringBuffer.append(this.TEXT_1685);
								}
								stringBuffer.append(this.TEXT_1686);
								stringBuffer.append(genFeature.getUpperName());
								stringBuffer.append(this.TEXT_1687);
								stringBuffer.append(genClass.getFlagsField(genFeature));
								stringBuffer.append(this.TEXT_1688);
								stringBuffer.append(genFeature.getUpperName());
								stringBuffer.append(this.TEXT_1689);
								stringBuffer.append(genFeature.getUpperName());
								stringBuffer.append(this.TEXT_1690);
								stringBuffer.append(genModel.getNonNLS());
							}
						} else {
							stringBuffer.append(this.TEXT_1691);
							if (genClass.isESetFlag(genFeature)) {
								stringBuffer.append(this.TEXT_1692);
								stringBuffer.append(genClass.getESetFlagsField(genFeature));
								stringBuffer.append(this.TEXT_1693);
								stringBuffer.append(genFeature.getUpperName());
								stringBuffer.append(this.TEXT_1694);
							} else {
								stringBuffer.append(genFeature.getUncapName());
								stringBuffer.append(this.TEXT_1695);
							}
							stringBuffer.append(this.TEXT_1696);
							stringBuffer.append(genFeature.getSafeName());
							stringBuffer.append(this.TEXT_1697);
							stringBuffer.append(genModel.getNonNLS());
						}
					} else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
						stringBuffer.append(this.TEXT_1698);
						stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
						stringBuffer.append(positiveOffsetCorrection);
						if (!genFeature.isListType() && !genFeature.isReferenceType()) {
							stringBuffer.append(this.TEXT_1699);
							stringBuffer.append(genFeature.getEDefault());
						}
						stringBuffer.append(this.TEXT_1700);
					} else if (genClass.isFlag(genFeature)) {
						if (genFeature.isBooleanType()) {
							stringBuffer.append(this.TEXT_1701);
							stringBuffer.append(genClass.getFlagsField(genFeature));
							stringBuffer.append(this.TEXT_1702);
							stringBuffer.append(genFeature.getUpperName());
							stringBuffer.append(this.TEXT_1703);
						} else {
							stringBuffer.append(this.TEXT_1704);
							stringBuffer.append(genFeature.getUpperName());
							stringBuffer.append(this.TEXT_1705);
							stringBuffer.append(genClass.getFlagsField(genFeature));
							stringBuffer.append(this.TEXT_1706);
							stringBuffer.append(genFeature.getUpperName());
							stringBuffer.append(this.TEXT_1707);
							stringBuffer.append(genFeature.getUpperName());
							stringBuffer.append(this.TEXT_1708);
						}
					} else {
						stringBuffer.append(this.TEXT_1709);
						stringBuffer.append(genFeature.getSafeName());
						stringBuffer.append(this.TEXT_1710);
					}
				}
			}
			stringBuffer.append(this.TEXT_1711);
		}
		if (isImplementation && genClass.isMapEntry()) {
			final GenFeature keyFeature = genClass.getMapEntryKeyFeature();
			final GenFeature valueFeature = genClass.getMapEntryValueFeature();
			final String objectType = genModel.getImportedName("java.lang.Object");
			final String keyType = isJDK50 ? keyFeature.getObjectType(genClass) : objectType;
			final String valueType = isJDK50 ? valueFeature.getObjectType(genClass) : objectType;
			final String eMapType = genModel.getImportedName("org.eclipse.emf.common.util.EMap")
					+ (isJDK50 ? "<" + keyType + ", " + valueType + ">" : "");
			stringBuffer.append(this.TEXT_1712);
			if (isGWT) {
				stringBuffer.append(this.TEXT_1713);
				stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
			}
			stringBuffer.append(this.TEXT_1714);
			stringBuffer.append(objectType);
			stringBuffer.append(this.TEXT_1715);
			stringBuffer.append(keyType);
			stringBuffer.append(this.TEXT_1716);
			if (!isJDK50 && keyFeature.isPrimitiveType()) {
				stringBuffer.append(this.TEXT_1717);
				stringBuffer.append(keyFeature.getObjectType(genClass));
				stringBuffer.append(this.TEXT_1718);
			} else {
				stringBuffer.append(this.TEXT_1719);
			}
			stringBuffer.append(this.TEXT_1720);
			stringBuffer.append(keyType);
			stringBuffer.append(this.TEXT_1721);
			if (keyFeature.isListType()) {
				stringBuffer.append(this.TEXT_1722);
				if (!genModel.useGenerics()) {
					stringBuffer.append(this.TEXT_1723);
					stringBuffer.append(genModel.getImportedName("java.util.Collection"));
					stringBuffer.append(this.TEXT_1724);
				}
				stringBuffer.append(this.TEXT_1725);
			} else if (isJDK50) {
				stringBuffer.append(this.TEXT_1726);
			} else if (keyFeature.isPrimitiveType()) {
				stringBuffer.append(this.TEXT_1727);
				stringBuffer.append(keyFeature.getObjectType(genClass));
				stringBuffer.append(this.TEXT_1728);
				stringBuffer.append(keyFeature.getPrimitiveValueFunction());
				stringBuffer.append(this.TEXT_1729);
			} else {
				stringBuffer.append(this.TEXT_1730);
				stringBuffer.append(keyFeature.getImportedType(genClass));
				stringBuffer.append(this.TEXT_1731);
			}
			stringBuffer.append(this.TEXT_1732);
			stringBuffer.append(valueType);
			stringBuffer.append(this.TEXT_1733);
			if (!isJDK50 && valueFeature.isPrimitiveType()) {
				stringBuffer.append(this.TEXT_1734);
				stringBuffer.append(valueFeature.getObjectType(genClass));
				stringBuffer.append(this.TEXT_1735);
			} else {
				stringBuffer.append(this.TEXT_1736);
			}
			stringBuffer.append(this.TEXT_1737);
			stringBuffer.append(valueType);
			stringBuffer.append(this.TEXT_1738);
			stringBuffer.append(valueType);
			stringBuffer.append(this.TEXT_1739);
			stringBuffer.append(valueType);
			stringBuffer.append(this.TEXT_1740);
			if (valueFeature.isListType()) {
				stringBuffer.append(this.TEXT_1741);
				if (!genModel.useGenerics()) {
					stringBuffer.append(this.TEXT_1742);
					stringBuffer.append(genModel.getImportedName("java.util.Collection"));
					stringBuffer.append(this.TEXT_1743);
				}
				stringBuffer.append(this.TEXT_1744);
			} else if (isJDK50) {
				stringBuffer.append(this.TEXT_1745);
			} else if (valueFeature.isPrimitiveType()) {
				stringBuffer.append(this.TEXT_1746);
				stringBuffer.append(valueFeature.getObjectType(genClass));
				stringBuffer.append(this.TEXT_1747);
				stringBuffer.append(valueFeature.getPrimitiveValueFunction());
				stringBuffer.append(this.TEXT_1748);
			} else {
				stringBuffer.append(this.TEXT_1749);
				stringBuffer.append(valueFeature.getImportedType(genClass));
				stringBuffer.append(this.TEXT_1750);
			}
			stringBuffer.append(this.TEXT_1751);
			if (genModel.useGenerics()) {
				stringBuffer.append(this.TEXT_1752);
			}
			stringBuffer.append(this.TEXT_1753);
			stringBuffer.append(eMapType);
			stringBuffer.append(this.TEXT_1754);
			stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
			stringBuffer.append(this.TEXT_1755);
			stringBuffer.append(eMapType);
			stringBuffer.append(this.TEXT_1756);
		}
		final String injectedCode = generatorAdapter.getInjectedCode(isImplementation);
		if (injectedCode != null) {
			stringBuffer.append(injectedCode);
		} else {
			// Do nothing
		}
		generatorAdapter.handleImports(isImplementation);
		stringBuffer.append(this.TEXT_1757);
		stringBuffer.append(isInterface ? " " + genClass.getInterfaceName() : genClass.getClassName());
		genModel.emitSortedImports();
		stringBuffer.append(this.TEXT_1758);
		return stringBuffer.toString();
	}
}
