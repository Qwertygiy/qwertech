package com.kbi.qwertech.api.data;

import gregapi.code.ModData;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.oredict.OreDictMaterial;

import java.util.HashMap;

public class WOOD {
	
	public static final OreDictMaterial[] woodList = new OreDictMaterial[256];
	public static final HashMap<String, Integer> woodMap = new HashMap<String, Integer>();
	
	public static final ModData
	NAT = new ModData("Natura", "Natura");
	
	public static final OreDictMaterial //id  	orename        	  	local name			who made it		red  	green   blue    trans    identical names                                                             
	Oak 				= 		makeWood(1, 	"Oak"				, "Oak",				MD.MC,		180, 	144, 	90, 	255													),
	Birch 				= 		makeWood(2, 	"Birch"				, "Birch",				MD.MC,		215, 	204, 	142, 	255													),
	Spruce 				= 		makeWood(3, 	"Spruce"			, "Spruce",				MD.MC,		102, 	79, 	47, 	255 	,"Pine"										),
	Jungle 				= 		makeWood(4, 	"Jungle"			, "Jungle",				MD.MC,		177, 	128, 	92, 	255													),
	DarkOak				= 		makeWood(5, 	"DarkOak"			, "Dark Oak",			MD.MC,		70, 	45, 	21, 	255 	,"Dark Oak"									),
	Acacia 				= 		makeWood(6, 	"Acacia"			, "Acacia",				MD.MC,		186, 	104, 	59, 	255													),
	
	Rubber 				= 		copyWood(10, MT.WoodRubber),
	Maple	 			= 		makeWood(11, 	"Maple"				, "Maple",				MD.GT,		151, 	26, 	26, 	255													),
	Willow	 			= 		makeWood(12, 	"Willow"			, "Willow",				MD.GT,		37, 	150, 	0, 		255													),
	BlueMahoe 			= 		makeWood(13, 	"BlueMahoe"			, "Blue Mahoe",			MD.GT,		15, 	103, 	254, 	255		,"Blue Mahoe"								),
	Hazel				= 		makeWood(14, 	"Hazel"				, "Hazel",				MD.GT,		228, 	175, 	175, 	255													),
	Cinnamon	 		= 		makeWood(15, 	"Cinnamon"			, "Cinnamon",			MD.GT,		65, 	192, 	192, 	255													),
	Compressed			= 		makeWood(16, 	"Compressed"		, "Compressed",			MD.GT,		94, 	60, 	25, 	255													),
	Dry		 			= 		makeWood(17, 	"Dry"				, "Dry",				MD.GT,		116, 	108, 	63, 	255		,"Dried","Dehydrated","Dead"				),
	Frozen	 			= 		makeWood(18, 	"Frozen"			, "Frozen",				MD.GT,		84, 	125, 	125, 	255		,"Icy"										),
	Mossy	 			= 		makeWood(19, 	"Mossy"				, "Mossy",				MD.GT,		29, 	127, 	0, 		255		,"Moss"										),
	Rainbowood 			= 		makeWood(20, 	"Rainbowood"		, "Rainbow Wood",		MD.GT,		200, 	64, 	245, 	255		,"Rainbowwood", "Rainbow Wood", "RainbowWood", "Rainbow"),
	Rotten	 			= 		makeWood(21, 	"Rotten"			, "Rotten",				MD.GT,		22, 	44, 	15, 	255		,"Rotted","Decayed","Rotting"				),
	Treated				=		copyWood(22, MT.WoodSealed),
	
	Autumn 				= 		makeWood(30, 	"Autumn"			, "Autumn", 			MD.EBXL,	191, 	64, 	35, 	255		,"Fall"										),
	BaldCypress			= 		makeWood(31, 	"BaldCypress"		, "Bald Cypress",		MD.EBXL,	195, 	183, 	171, 	255		,"Bald Cypress"								),
	Cypress				= 		makeWood(32, 	"Cypress"			, "Cypress",			MD.EBXL,	185, 	187, 	181, 	255													),
	Fir					= 		makeWood(33,	 "Fir"				, "Fir",				MD.EBXL,	110, 	106, 	63, 	255													),
	JapaneseMaple		= 		makeWood(34,	 "JapaneseMaple"	, "Japanese Maple",		MD.EBXL,	152, 	76, 	86, 	255													),
	RainbowEucalyptus	= 		makeWood(35,	 "RainbowEucalyptus", "Rainbow Eucalyptus",	MD.EBXL,	116, 	141, 	198, 	255		,"Rainbow Eucalyptus"						),
	Redwood				= 		makeWood(36,	 "Redwood"			, "Redwood",			MD.EBXL,	163, 	115, 	70, 	255		,"RedWood"									),
	Sakura				= 		makeWood(37,	 "Sakura"			, "Sakura",				MD.EBXL,	250, 	161, 	122, 	255													),
				
	Balsa				= 		makeWood(40,	 "Balsa"			, "Balsa",				MD.FR,		165, 	158, 	151, 	255													),
	Baobab				= 		makeWood(41,	 "Baobab"			, "Baobab",				MD.FR,		136, 	145, 	95, 	255													),
	Cherry				= 		makeWood(42,	 "Cherry"			, "Cherry",				MD.FR,		173, 	124, 	50, 	255													),
	Chestnut			= 		makeWood(43,	 "Chestnut"			, "Chestnut",			MD.FR,		179, 	162, 	85, 	255													),		
	Citrus				= 		makeWood(44,	 "Citrus"			, "Citrus",				MD.FR,		152, 	163, 	28, 	255		,"Orange","Lemon","Grapefruit"				),
	Cocobolo			= 		makeWood(45,	 "Cocobolo"			, "Cocobolo",			MD.FR,		121, 	18, 	2, 		255													),
	Ebony				= 		makeWood(46,	 "Ebony"			, "Ebony",				MD.FR,		58, 	52, 	46, 	255													),
	Giganteum			= 		makeWood(47,	 "Giganteum"		, "Giganteum",			MD.FR,		102, 	47, 	39, 	255													),
	Greenheart			= 		makeWood(48,	 "Greenheart"		, "Greenheart",			MD.FR,		76, 	118, 	88, 	255													),
	Ipe					= 		makeWood(49,	 "Ipe"				, "Ipe",				MD.FR,		101, 	58, 	39, 	255													),
	Kapok				= 		makeWood(50,	 "Kapok"			, "Kapok",				MD.FR,		116, 	108, 	52, 	255													),
	Larch				= 		makeWood(51,	 "Larch"			, "Larch",				MD.FR,		215, 	151, 	133, 	255													),
	Lime				= 		makeWood(52,	 "Lime"				, "Lime",				MD.FR,		206, 	154, 	104, 	255													),
	Mahoe				= 		makeWood(53,	 "Mahoe"			, "Mahoe",				MD.FR,		121, 	147, 	166, 	255													),
	Mahogany			= 		makeWood(54,	 "Mahogany"			, "Mahogany",			MD.FR,		111, 	61, 	55, 	255													),
	Padauk				= 		makeWood(55,	 "Padauk"			, "Padauk",				MD.FR,		179, 	99, 	59, 	255		,"Paduak"									),
	Palm				= 		makeWood(56,	 "Palm"				, "Palm",				MD.FR,		201, 	124, 	69, 	255													),
	Papaya				= 		makeWood(57,	 "Papaya"			, "Papaya",				MD.FR,		218, 	200, 	109, 	255													),
	Plum				= 		makeWood(58,	 "Plum"				, "Plum",				MD.FR,		171, 	99, 	123, 	255													),
	Poplar				= 		makeWood(59,	 "Poplar"			, "Poplar",				MD.FR,		204, 	204, 	123, 	255													),
	Sequoia				= 		makeWood(60,	 "Sequoia"			, "Sequoia",			MD.FR,		142, 	87, 	84, 	255													),
	Teak				= 		makeWood(61,	 "Teak"				, "Teak",				MD.FR,		123, 	115, 	95, 	255													),
	Walnut				= 		makeWood(62,	 "Walnut"			, "Walnut",				MD.FR,		98, 	78, 	64, 	255													),
	Wenge				= 		makeWood(63,	 "Wenge"			, "Wenge",				MD.FR,		88, 	81, 	70, 	255													),
	Zebrawood			= 		makeWood(64,	 "Zebrawood"		, "Zebrawood",			MD.FR,		172, 	139, 	86, 	255		,"Zebra"									),
	
	Dark				= 		makeWood(70,	 "Dark"				, "Dark",				MD.BoP,		51, 	45, 	54, 	255		,"Darkwood","DarkWood"						),
	Ethereal			= 		makeWood(71,	 "Ethereal"			, "Ethereal",			MD.BoP,		76, 	150, 	115, 	255													),
	Gold				= 		makeWood(72,	 "Gold"				, "Gold",				MD.BoP,		210, 	187, 	151, 	255		,"Golden"									),
	HellBark			= 		makeWood(73,	 "Hellbark"			, "Hellbark",			MD.BoP,		200, 	150, 	100, 	255													),		
	Jacaranda			= 		makeWood(74,	 "Jacaranda"		, "Jacaranda",			MD.BoP,		201, 	171, 	162, 	255													),
	Magic				= 		makeWood(75,	 "Magic"			, "Magic",				MD.BoP,		90, 	105, 	180, 	255		,"Magical"									),
	Mangrove			= 		makeWood(76,	 "Mangrove"			, "Mangrove",			MD.BoP,		236, 	228, 	217, 	255													),
	SacredOak			= 		makeWood(77,	 "SacredOak"		, "SacredOak",			MD.BoP,		159, 	132, 	77, 	255		,"OakSacred","Sacred Oak"					),
	
	Greatwood			= 		makeWood(80,	 "Greatwood"		, "Greatwood",			MD.TC,		47, 	33, 	29, 	255		,"GreatWood","Great"						),
	Silverwood			= 		makeWood(81,	 "Silverwood"		, "Silverwood",			MD.TC,		230, 	230, 	235, 	255		,"SilverWood","Silver"						),
	Alder				= 		makeWood(82,	 "Alder"			, "Alder",				MD.WTCH,	177, 	95, 	87, 	255													),
	Hawthorn			= 		makeWood(83,	 "Hawthorn"			, "Hawthorn",			MD.WTCH,	188, 	182, 	178, 	255													),
	Rowan				= 		makeWood(84,	 "Rowan"			, "Rowan",				MD.WTCH,	205, 	172, 	87, 	255													),
	
	Apple				= 		makeWood(90,	 "Apple"			, "Apple",			MD.BINNIE_TREE,	97, 	49, 	36, 	255		,"Applewood","AppleWood"					),
	Ash					= 		makeWood(91,	 "Ash"				, "Ash",			MD.BINNIE_TREE,	244, 	190, 	90, 	255													),
	Beech				= 		makeWood(92,	 "Beech"			, "Beech",			MD.BINNIE_TREE,	226, 	144, 	68, 	255		,"Beechwood","BeechWood"					),
	Box					= 		makeWood(93,	 "Box"				, "Box",			MD.BINNIE_TREE,	253, 	237, 	192, 	255		,"Boxwood","BoxWood"						),
	Brazilwood			= 		makeWood(94,	 "Brazilwood"		, "Brazilwood",		MD.BINNIE_TREE,	112, 	55, 	84, 	255		,"Brazil","BrazilWood"						),
	Butternut			= 		makeWood(95,	 "Butternut"		, "Butternut",		MD.BINNIE_TREE,	237, 	163, 	112, 	255													),
	Cedar				= 		makeWood(96,	 "Cedar"			, "Cedar",			MD.BINNIE_TREE,	217, 	88, 	37, 	255													),
	Elder				= 		makeWood(97,	 "Elder"			, "Elder",			MD.BINNIE_TREE,	189, 	141, 	115, 	255													),
	Elm					= 		makeWood(98,	 "Elm"				, "Elm",			MD.BINNIE_TREE,	243, 	163, 	90, 	255													),
	Eucalyptus			= 		makeWood(99,	 "Eucalyptus"		, "Eucalyptus",		MD.BINNIE_TREE,	245, 	164, 	130, 	255													),
	Fig					= 		makeWood(100,	 "Fig"				, "Fig",			MD.BINNIE_TREE,	202, 	126, 	27, 	255		,"Figwood","FigWood"						),
	Gingko				= 		makeWood(101,	 "Gingko"			, "Gingko",			MD.BINNIE_TREE,	243, 	226, 	173, 	255													),
	Hemlock				= 		makeWood(102,	 "Hemlock"			, "Hemlock",		MD.BINNIE_TREE,	196, 	174, 	96, 	255													),
	Hickory				= 		makeWood(103,	 "Hickory"			, "Hickory",		MD.BINNIE_TREE,	218, 	174, 	134, 	255													),
	Holly				= 		makeWood(104,	 "Holly"			, "Holly",			MD.BINNIE_TREE,	248, 	242, 	226, 	255													),
	Hornbeam			= 		makeWood(105,	 "Hornbeam"			, "Hornbeam",		MD.BINNIE_TREE,	195, 	147, 	87, 	255													),
	Iroko				= 		makeWood(106,	 "Iroko"			, "Iroko",			MD.BINNIE_TREE,	117, 	47, 	0,	 	255													),
	Locust				= 		makeWood(107,	 "Locust"			, "Locust",			MD.BINNIE_TREE,	195, 	140, 	87, 	255													),
	Logwood				= 		makeWood(108,	 "Logwood"			, "Logwood",		MD.BINNIE_TREE,	166, 	44, 	34, 	255													),
	Maclura				= 		makeWood(109,	 "Maclura"			, "Maclura",		MD.BINNIE_TREE,	242, 	168, 	29, 	255													),
	Olive				= 		makeWood(110,	 "Olive"			, "Olive",			MD.BINNIE_TREE,	174, 	169, 	129, 	255													),
	Pear				= 		makeWood(111,	 "Pear"				, "Pear",			MD.BINNIE_TREE,	180, 	127, 	97, 	255		,"Pearwood","PearWood"						),
	PinkIvory			= 		makeWood(112,	 "PinkIvory"		, "Pink Ivory",		MD.BINNIE_TREE,	234, 	125, 	148, 	255		,"IvoryPink"								),
	Purpleheart			= 		makeWood(113,	 "Purpleheart"		, "Purpleheart",	MD.BINNIE_TREE,	91, 	22, 	45, 	255													),
	Rosewood			= 		makeWood(114,	 "Rosewood"			, "Rosewood",		MD.BINNIE_TREE,	128, 	12, 	0,	 	255		,"Rose","RoseWood"							),
	Sweetgum			= 		makeWood(115,	 "Sweetgum"			, "Sweetgum",		MD.BINNIE_TREE,	215, 	140, 	74, 	255													),
	Syzgium				= 		makeWood(116,	 "Syzgium"			, "Syzgium",		MD.BINNIE_TREE,	221, 	184, 	183, 	255													),
	Whitebeam			= 		makeWood(117,	 "Whitebeam"		, "Whitebeam",		MD.BINNIE_TREE,	192, 	183, 	174, 	255		,"WhiteBeam"								),
	Yew					= 		makeWood(118,	 "Yew"				, "Yew",			MD.BINNIE_TREE,	226, 	160, 	114, 	255													),
	
	Towerwood			= 		makeWood(120,	 "Towerwood"		, "Towerwood",			MD.TF,		166, 	101, 	58, 	255		,"Tower","TowerWood"						),
	
	Witchwood			= 		makeWood(121,	 "Witchwood"		, "Witchwood",			MD.ARS,		118, 	112, 	142, 	255		,"Witch","WitchWood"						),
	
	Shimmerwood			= 		makeWood(122,	 "Shimmerwood"		, "Shimmerwood",		MD.BOTA,	255, 	0, 		0,	 	255		,"Shimmer","ShimmerWood"					),
			
	Bloodwood			= 		makeWood(125,	 "Bloodwood"		, "Bloodwood",			NAT,		151, 	38, 	23,	 	255		,"Blood","BloodWood"						),
	Fusewood			= 		makeWood(126,	 "Fusewood"			, "Fusewood",			NAT,		81, 	117, 	111, 	255		,"Fuse","FuseWood"							),
	Ghostwood			= 		makeWood(127,	 "Ghostwood"		, "Ghostwood",			NAT,		191, 	191, 	191, 	255		,"Ghost","GhostWood"						),
	Hopseed				= 		makeWood(128,	 "Hopseed"			, "Hopseed",			NAT,		207, 	158, 	127, 	255													),
	Silverbell			= 		makeWood(129,	 "Silverbell"		, "Silverbell",			NAT,		198, 	193, 	174, 	255													),
	Tiger				= 		makeWood(130,	 "Tiger"			, "Tiger",				NAT,		164, 	98, 	0,	 	255		,"Tigerwood","TigerWood"					),
	
	Ogre				= 		makeWood(135,	 "Ogre"				, "Ogre",				MD.MoCr,	180, 	90, 	106,	255		,"Ogrewood","OgreWood"						),
	Wyvern				= 		makeWood(136,	 "Wyvern"			, "Wyvern",				MD.MoCr,	90, 	180, 	72,		255		,"Wyvernwood","WyvernWood"					),
	
	Aspen				= 		makeWood(140,	 "Aspen"			, "Aspen",				MD.TFC,		68, 	65, 	50,		255													),
	DouglasFir			= 		makeWood(141,	 "DouglasFir"		, "Douglas Fir",		MD.TFC,		249, 	197, 	154,	255		,"Douglas Fir","Douglasfir"					),
	Sycamore			= 		makeWood(142,	 "Sycamore"			, "Sycamore",			MD.TFC,		214, 	155, 	69,		255													),
	WhiteCedar			= 		makeWood(143,	 "WhiteCedar"		, "White Cedar",		MD.TFC,		219, 	219, 	205,	255		,"White Cedar","Whitecedar"					),
	WhiteElm			= 		makeWood(144,	 "WhiteElm"			, "White Elm",			MD.TFC,		162, 	167, 	103,	255		,"White Elm","Whiteelm"						),
											
	QuakingAspen		=		makeWood(150,	 "QuakingAspen"		, "Quaking Aspen",		MD.QT,		219,	201,	177,	255		,"Quaking Aspen","AmericanAspen"			),
	CommonAspen			=		makeWood(151,	 "CommonAspen"		, "Common Aspen",		MD.QT,		218,	180,	141,	255		,"Common Aspen","EuropeanAspen"				),
	WhitePoplar			=		makeWood(152,	 "WhitePoplar"		, "White Poplar",		MD.QT,		205,	171,	133,	255		,"White Poplar","Abele"						),
	BoxleafAzara		=		makeWood(153,	 "BoxleafAzara"		, "Boxleaf Azara",		MD.QT,		210,	203,	180,	255		,"Azara","Boxleaf Azara"					),
	SouthernMagnolia	=		makeWood(154,	 "SouthernMagnolia"	, "Southern Magnolia",	MD.QT,		185,	173,	151,	255		,"Southern Magnolia"						),
	Sweetbay			=		makeWood(155,	 "Sweetbay"			, "Sweetbay",			MD.QT,		244,	213,	156,	255		,"SwampMagnolia","Swamp Magnolia"			),
	Marblewood			=		makeWood(156,	 "Marblewood"		, "Marblewood",			MD.QT,		177,	125,	94,		255		,"Marble Wood","MarbleWood"					),
	TamoAsh				=		makeWood(157,	 "TamoAsh"			, "Tamo Ash",			MD.QT,		160,	115,	56,		255		,"Tamo Ash","JapaneseAsh"					),
	WhiteMeranti		=		makeWood(158,	 "WhiteMeranti"		, "White Meranti",		MD.QT,		178,	126,	68,		255 	,"White Meranti","PhillipineMahogany"		),
	
	Default 			= 		copyWood(0, MT.Wood);

	public static OreDictMaterial makeWood(int ID, String oreName, String local, ModData originalMod, int red, int green, int blue, int alpha, String... identicalNames)
	{
		for (int q = 0; q < identicalNames.length; q++)
		{
			identicalNames[q] = "Wood" + identicalNames[q];
		}
		OreDictMaterial returnable = OreDictMaterial.createMaterial(-1, "Wood" + oreName, local).steal(MT.Wood).setOriginalMod(originalMod).setRGBa(red, green, blue, alpha).addIdenticalNames(identicalNames);
		woodList[ID] = returnable;
		woodMap.put("Wood" + oreName, ID);
		return returnable;
	}
	
	public static OreDictMaterial copyWood(int ID, OreDictMaterial toAssign)
	{
		woodList[ID] = toAssign;
		woodMap.put(toAssign.mNameInternal, ID);
		return toAssign;
	}
}
