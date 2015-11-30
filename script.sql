USE [master]
GO
/****** Object:  Database [FairDeal]    Script Date: 11/5/2015 2:22:38 AM ******/
CREATE DATABASE [FairDeal]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'FairDeal', FILENAME = N'H:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\FairDeal.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'FairDeal_log', FILENAME = N'H:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\FairDeal_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [FairDeal] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [FairDeal].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [FairDeal] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [FairDeal] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [FairDeal] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [FairDeal] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [FairDeal] SET ARITHABORT OFF 
GO
ALTER DATABASE [FairDeal] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [FairDeal] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [FairDeal] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [FairDeal] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [FairDeal] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [FairDeal] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [FairDeal] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [FairDeal] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [FairDeal] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [FairDeal] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [FairDeal] SET  DISABLE_BROKER 
GO
ALTER DATABASE [FairDeal] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [FairDeal] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [FairDeal] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [FairDeal] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [FairDeal] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [FairDeal] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [FairDeal] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [FairDeal] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [FairDeal] SET  MULTI_USER 
GO
ALTER DATABASE [FairDeal] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [FairDeal] SET DB_CHAINING OFF 
GO
ALTER DATABASE [FairDeal] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [FairDeal] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [FairDeal]
GO
/****** Object:  Table [dbo].[HAM_Action]    Script Date: 11/5/2015 2:22:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HAM_Action](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NULL,
	[IsActive] [bit] NULL,
 CONSTRAINT [PK_Action] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HAM_BillOrder]    Script Date: 11/5/2015 2:22:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HAM_BillOrder](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[OrderDate] [datetime] NULL,
	[SellerID] [int] NOT NULL,
	[CustomerID] [int] NULL,
	[IsActive] [bit] NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HAM_Category]    Script Date: 11/5/2015 2:22:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HAM_Category](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NULL,
	[IsActive] [bit] NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HAM_Customer]    Script Date: 11/5/2015 2:22:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[HAM_Customer](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[CustomerName] [nvarchar](50) NULL,
	[ContactNo] [varchar](20) NULL,
	[Address] [nvarchar](200) NULL,
	[IsActive] [bit] NULL,
 CONSTRAINT [PK_Customer] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[HAM_Diary]    Script Date: 11/5/2015 2:22:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HAM_Diary](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[AccountID] [int] NULL,
	[Time] [datetime] NULL,
	[Description] [nvarchar](200) NULL,
 CONSTRAINT [PK_Diary] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HAM_Employee]    Script Date: 11/5/2015 2:22:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[HAM_Employee](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NULL,
	[Address] [nvarchar](200) NULL,
	[ContactNo] [varchar](20) NULL,
	[Username] [varchar](55) NULL,
	[Password] [varchar](50) NULL,
	[Department] [nvarchar](100) NULL,
	[RoleID] [int] NULL,
	[IsActive] [bit] NULL,
 CONSTRAINT [PK_Employee] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[HAM_OrderLine]    Script Date: 11/5/2015 2:22:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HAM_OrderLine](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[OrderID] [int] NOT NULL,
	[ProductID] [int] NOT NULL,
	[Quantity] [int] NOT NULL,
	[ActualSalePrice] [decimal](18, 2) NOT NULL,
 CONSTRAINT [PK_OrderLine] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HAM_Product]    Script Date: 11/5/2015 2:22:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HAM_Product](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[ProductName] [nvarchar](200) NULL,
	[ProductDescription] [nvarchar](max) NULL,
	[ExpectedSalePrice] [decimal](18, 2) NOT NULL,
	[Quantity] [int] NULL,
	[CategoryID] [int] NULL,
	[ImportPrice] [decimal](18, 2) NOT NULL,
	[IsActive] [bit] NULL,
	[ImportDate] [datetime] NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HAM_Role]    Script Date: 11/5/2015 2:22:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HAM_Role](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NULL,
	[IsActive] [bit] NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HAM_Role_Action]    Script Date: 11/5/2015 2:22:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HAM_Role_Action](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[RoleID] [int] NOT NULL,
	[ActionID] [int] NOT NULL,
 CONSTRAINT [PK_Role_Action] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[HAM_Action] ON 

INSERT [dbo].[HAM_Action] ([ID], [Name], [IsActive]) VALUES (1, N'Account Management', 1)
INSERT [dbo].[HAM_Action] ([ID], [Name], [IsActive]) VALUES (2, N'Account Permission', 1)
INSERT [dbo].[HAM_Action] ([ID], [Name], [IsActive]) VALUES (3, N'Product Management', 1)
INSERT [dbo].[HAM_Action] ([ID], [Name], [IsActive]) VALUES (4, N'Sell Product', 1)
INSERT [dbo].[HAM_Action] ([ID], [Name], [IsActive]) VALUES (5, N'Bill Overview', 1)
INSERT [dbo].[HAM_Action] ([ID], [Name], [IsActive]) VALUES (6, N'Change Password', 1)
INSERT [dbo].[HAM_Action] ([ID], [Name], [IsActive]) VALUES (7, N'Category Management', 1)
INSERT [dbo].[HAM_Action] ([ID], [Name], [IsActive]) VALUES (8, N'Customer Management', 1)
INSERT [dbo].[HAM_Action] ([ID], [Name], [IsActive]) VALUES (9, N'Report', 1)
INSERT [dbo].[HAM_Action] ([ID], [Name], [IsActive]) VALUES (10, N'Backup', 1)
INSERT [dbo].[HAM_Action] ([ID], [Name], [IsActive]) VALUES (11, N'Restore', 1)
INSERT [dbo].[HAM_Action] ([ID], [Name], [IsActive]) VALUES (12, N'Diary', 1)
SET IDENTITY_INSERT [dbo].[HAM_Action] OFF
SET IDENTITY_INSERT [dbo].[HAM_BillOrder] ON 

INSERT [dbo].[HAM_BillOrder] ([ID], [OrderDate], [SellerID], [CustomerID], [IsActive]) VALUES (1, CAST(0x0000A54400C1C2E2 AS DateTime), 1, 1, 1)
INSERT [dbo].[HAM_BillOrder] ([ID], [OrderDate], [SellerID], [CustomerID], [IsActive]) VALUES (2, CAST(0x0000A545015113E2 AS DateTime), 1, 1, 1)
INSERT [dbo].[HAM_BillOrder] ([ID], [OrderDate], [SellerID], [CustomerID], [IsActive]) VALUES (3, CAST(0x0000A54501519F77 AS DateTime), 1, 1, 1)
INSERT [dbo].[HAM_BillOrder] ([ID], [OrderDate], [SellerID], [CustomerID], [IsActive]) VALUES (4, CAST(0x0000A5450151C197 AS DateTime), 1, 1, 1)
INSERT [dbo].[HAM_BillOrder] ([ID], [OrderDate], [SellerID], [CustomerID], [IsActive]) VALUES (5, CAST(0x0000A5450151DE1A AS DateTime), 1, 1, 1)
INSERT [dbo].[HAM_BillOrder] ([ID], [OrderDate], [SellerID], [CustomerID], [IsActive]) VALUES (6, CAST(0x0000A5450151FA94 AS DateTime), 1, 1, 1)
INSERT [dbo].[HAM_BillOrder] ([ID], [OrderDate], [SellerID], [CustomerID], [IsActive]) VALUES (7, CAST(0x0000A54501520D47 AS DateTime), 1, 1, 1)
INSERT [dbo].[HAM_BillOrder] ([ID], [OrderDate], [SellerID], [CustomerID], [IsActive]) VALUES (8, CAST(0x0000A54501522F48 AS DateTime), 1, 1, 1)
INSERT [dbo].[HAM_BillOrder] ([ID], [OrderDate], [SellerID], [CustomerID], [IsActive]) VALUES (9, CAST(0x0000A545015243CF AS DateTime), 1, 1, 1)
INSERT [dbo].[HAM_BillOrder] ([ID], [OrderDate], [SellerID], [CustomerID], [IsActive]) VALUES (10, CAST(0x0000A545015257F4 AS DateTime), 1, 1, 1)
INSERT [dbo].[HAM_BillOrder] ([ID], [OrderDate], [SellerID], [CustomerID], [IsActive]) VALUES (11, CAST(0x0000A54501528683 AS DateTime), 1, 1, 1)
INSERT [dbo].[HAM_BillOrder] ([ID], [OrderDate], [SellerID], [CustomerID], [IsActive]) VALUES (12, CAST(0x0000A5450152A49A AS DateTime), 1, 1, 1)
INSERT [dbo].[HAM_BillOrder] ([ID], [OrderDate], [SellerID], [CustomerID], [IsActive]) VALUES (13, CAST(0x0000A54501627627 AS DateTime), 1, 1, 1)
INSERT [dbo].[HAM_BillOrder] ([ID], [OrderDate], [SellerID], [CustomerID], [IsActive]) VALUES (14, CAST(0x0000A5460087AC01 AS DateTime), 1, 1, 1)
INSERT [dbo].[HAM_BillOrder] ([ID], [OrderDate], [SellerID], [CustomerID], [IsActive]) VALUES (15, CAST(0x0000A54600886CB2 AS DateTime), 1, 4, 1)
INSERT [dbo].[HAM_BillOrder] ([ID], [OrderDate], [SellerID], [CustomerID], [IsActive]) VALUES (16, CAST(0x0000A5460088F70A AS DateTime), 1, 4, 1)
SET IDENTITY_INSERT [dbo].[HAM_BillOrder] OFF
SET IDENTITY_INSERT [dbo].[HAM_Category] ON 

INSERT [dbo].[HAM_Category] ([ID], [Name], [IsActive]) VALUES (1, N'Sofas', 1)
INSERT [dbo].[HAM_Category] ([ID], [Name], [IsActive]) VALUES (2, N'Loveseats', 1)
INSERT [dbo].[HAM_Category] ([ID], [Name], [IsActive]) VALUES (3, N'Sectional Sofas', 1)
INSERT [dbo].[HAM_Category] ([ID], [Name], [IsActive]) VALUES (4, N'Sleeper Sofas', 1)
INSERT [dbo].[HAM_Category] ([ID], [Name], [IsActive]) VALUES (5, N'Power Sofas, Loveseats & Recliners', 1)
INSERT [dbo].[HAM_Category] ([ID], [Name], [IsActive]) VALUES (6, N'Beds', 1)
INSERT [dbo].[HAM_Category] ([ID], [Name], [IsActive]) VALUES (7, N'Nightstands', 1)
INSERT [dbo].[HAM_Category] ([ID], [Name], [IsActive]) VALUES (8, N'Dressers & Mirrors', 1)
INSERT [dbo].[HAM_Category] ([ID], [Name], [IsActive]) VALUES (9, N'Chest of Drawers', 1)
INSERT [dbo].[HAM_Category] ([ID], [Name], [IsActive]) VALUES (10, N'Benches', 1)
INSERT [dbo].[HAM_Category] ([ID], [Name], [IsActive]) VALUES (11, N'Dining Tables', 1)
INSERT [dbo].[HAM_Category] ([ID], [Name], [IsActive]) VALUES (12, N'Desks', 1)
INSERT [dbo].[HAM_Category] ([ID], [Name], [IsActive]) VALUES (13, N'Dining Chairs', 1)
INSERT [dbo].[HAM_Category] ([ID], [Name], [IsActive]) VALUES (14, N'Barstools', 1)
INSERT [dbo].[HAM_Category] ([ID], [Name], [IsActive]) VALUES (15, N'Dining Storage', 1)
SET IDENTITY_INSERT [dbo].[HAM_Category] OFF
SET IDENTITY_INSERT [dbo].[HAM_Customer] ON 

INSERT [dbo].[HAM_Customer] ([ID], [CustomerName], [ContactNo], [Address], [IsActive]) VALUES (1, N'Vo Thanh Son', N'0123456789', N'California St', 1)
INSERT [dbo].[HAM_Customer] ([ID], [CustomerName], [ContactNo], [Address], [IsActive]) VALUES (2, N'Luong Thi Phuong Thao', N'0123456789', N'California St', 1)
INSERT [dbo].[HAM_Customer] ([ID], [CustomerName], [ContactNo], [Address], [IsActive]) VALUES (3, N'Phan Hoang Hai', N'0129382484', N'31 Wall Street', 1)
INSERT [dbo].[HAM_Customer] ([ID], [CustomerName], [ContactNo], [Address], [IsActive]) VALUES (4, N'Amelia Lovegood', N'05293847173', N'09 Green Wood', 1)
INSERT [dbo].[HAM_Customer] ([ID], [CustomerName], [ContactNo], [Address], [IsActive]) VALUES (5, N'Melisa Bush', N'03928716532', N'12 Oak Land', 1)
INSERT [dbo].[HAM_Customer] ([ID], [CustomerName], [ContactNo], [Address], [IsActive]) VALUES (6, N'Duong Tuan', N'0129382484', N'365/11 Ngu Binh', 1)
SET IDENTITY_INSERT [dbo].[HAM_Customer] OFF
SET IDENTITY_INSERT [dbo].[HAM_Diary] ON 

INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (1, 1, CAST(0x0000A54400ACFD9A AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (2, 1, CAST(0x0000A54400AF1EF1 AS DateTime), N'Created category "Sofas"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (3, 1, CAST(0x0000A54400AF276B AS DateTime), N'Created category "Loveseats"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (4, 1, CAST(0x0000A54400AF2E8E AS DateTime), N'Created category "Sectional Sofas"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (5, 1, CAST(0x0000A54400AF3B9A AS DateTime), N'Created category "Sleeper Sofas"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (6, 1, CAST(0x0000A54400AF5254 AS DateTime), N'Created category "Power Sofas, Loveseats & Recliners"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (7, 1, CAST(0x0000A54400AF715E AS DateTime), N'Created category "Beds"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (8, 1, CAST(0x0000A54400AF79DA AS DateTime), N'Created category "Nightstands"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (9, 1, CAST(0x0000A54400AF808E AS DateTime), N'Created category "Dressers & Mirrors"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (10, 1, CAST(0x0000A54400AF87B3 AS DateTime), N'Created category "Chest of Drawers"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (11, 1, CAST(0x0000A54400AF8E04 AS DateTime), N'Created category "Benches"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (12, 1, CAST(0x0000A54400AFA93F AS DateTime), N'Created category "Dining Tables"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (13, 1, CAST(0x0000A54400AFB076 AS DateTime), N'Created category "Dining Sets"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (14, 1, CAST(0x0000A54400AFB79F AS DateTime), N'Created category "Dining Chairs"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (15, 1, CAST(0x0000A54400AFBF6B AS DateTime), N'Created category "Barstools"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (16, 1, CAST(0x0000A54400AFE373 AS DateTime), N'Created category "Dining Storage"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (17, 1, CAST(0x0000A54400AFED30 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (18, 1, CAST(0x0000A54400B032E5 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (19, 1, CAST(0x0000A54400B0A461 AS DateTime), N'Created product "Darcy Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (20, 1, CAST(0x0000A54400B0CE3A AS DateTime), N'Created product "Gypsum Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (21, 1, CAST(0x0000A54400B0F722 AS DateTime), N'Edited product "Darcy Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (22, 1, CAST(0x0000A54400B10DF7 AS DateTime), N'Edited product "Gypsum Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (23, 1, CAST(0x0000A54400B13BFD AS DateTime), N'Created product "Hodan Sofa Chaise"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (24, 1, CAST(0x0000A54400B16455 AS DateTime), N'Created product "Kinlock Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (25, 1, CAST(0x0000A54400B180E8 AS DateTime), N'Created product "Hariston Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (26, 1, CAST(0x0000A54400B1B3C5 AS DateTime), N'Created product "Darcy Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (27, 1, CAST(0x0000A54400B1D0B2 AS DateTime), N'Created product "Hodan Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (28, 1, CAST(0x0000A54400B1F626 AS DateTime), N'Created product "Stringer Reclining Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (29, 1, CAST(0x0000A54400B23616 AS DateTime), N'Created product "Pierin Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (30, 1, CAST(0x0000A54400B24B12 AS DateTime), N'Edited product "Pierin Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (31, 1, CAST(0x0000A54400B279FC AS DateTime), N'Created product "Ballari Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (32, 1, CAST(0x0000A54400B2B643 AS DateTime), N'Created product "Geordie Sofa Chaise"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (33, 1, CAST(0x0000A54400B2BF89 AS DateTime), N'Edited product "Ballari Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (34, 1, CAST(0x0000A54400B2E635 AS DateTime), N'Created product "Patola Park 4-Piece Sectional"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (35, 1, CAST(0x0000A54400B2F3C9 AS DateTime), N'Edited product "Patola Park 4-Piece Sectional"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (36, 1, CAST(0x0000A54400B32EA6 AS DateTime), N'Created product "Damacio 6-Piece Sectional With Power"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (37, 1, CAST(0x0000A54400B36124 AS DateTime), N'Created product "Loric 3-Piece Sectional"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (38, 1, CAST(0x0000A54400B3C1AA AS DateTime), N'Created product "Jessa Place 3-Piece Sectional"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (39, 1, CAST(0x0000A54400B3F6F5 AS DateTime), N'Created product "Alliston DuraBlend® Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (40, 1, CAST(0x0000A54400B41212 AS DateTime), N'Created product "Corley Queen Sofa Sleeper"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (41, 1, CAST(0x0000A54400B43B7A AS DateTime), N'Created product "Zeth Twin Sofa Sleeper"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (42, 1, CAST(0x0000A54400B45A14 AS DateTime), N'Created product "Ballari Queen Sofa Sleeper"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (43, 1, CAST(0x0000A54400B48976 AS DateTime), N'Created product "Dinelli Queen Sofa Sleeper"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (44, 1, CAST(0x0000A54400B4C5F7 AS DateTime), N'Created product "Oberson Power Reclining Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (45, 1, CAST(0x0000A54400B4E2FD AS DateTime), N'Created product "McNeil Power Recliner"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (46, 1, CAST(0x0000A54400B526C9 AS DateTime), N'Created product "Exhilaration Power Reclining Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (47, 1, CAST(0x0000A54400B55310 AS DateTime), N'Created product "Darshmore Power Recliner"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (48, 1, CAST(0x0000A54400B5842A AS DateTime), N'Created product "Beadle Power Recliner"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (49, 1, CAST(0x0000A54400B5B342 AS DateTime), N'Created product "Porter Queen Panel Bed"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (50, 1, CAST(0x0000A54400B5EAC1 AS DateTime), N'Created product "Corraya Queen Panel Bed"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (51, 1, CAST(0x0000A54400B6019E AS DateTime), N'Created product "Prentice Queen Panel Bed"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (52, 1, CAST(0x0000A54400B623C5 AS DateTime), N'Created product "Wesling Queen Panel Bed"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (53, 1, CAST(0x0000A54400B6536A AS DateTime), N'Created product "Sorinella Queen Upholstered Bed"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (54, 1, CAST(0x0000A54400B6A485 AS DateTime), N'Created product "Tanshire Nightstand"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (55, 1, CAST(0x0000A54400B6BF54 AS DateTime), N'Created product "Moriann Nightstand"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (56, 1, CAST(0x0000A54400B6F09F AS DateTime), N'Created product "Kaslyn Nightstand"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (57, 1, CAST(0x0000A54400B7113A AS DateTime), N'Created product "Dexifield Nightstand"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (58, 1, CAST(0x0000A54400B72F0C AS DateTime), N'Created product "Prentice Nightstand"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (59, 1, CAST(0x0000A54400B76B0E AS DateTime), N'Created product "Cottage Retreat Dresser And Mirror"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (60, 1, CAST(0x0000A54400B78457 AS DateTime), N'Created product "Larimer Dresser And Mirror"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (61, 1, CAST(0x0000A54400B7D016 AS DateTime), N'Created product "Kira Dresser"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (62, 1, CAST(0x0000A54400B82308 AS DateTime), N'Created product "Harlinton Dresser And Mirror"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (63, 1, CAST(0x0000A54400B84408 AS DateTime), N'Created product "Zenfield Dresser And Mirror"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (64, 1, CAST(0x0000A54400B89CAF AS DateTime), N'Created product "Kaslyn Chest Of Drawers"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (65, 1, CAST(0x0000A54400B8B733 AS DateTime), N'Created product "Fanzere Chest Of Drawers"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (66, 1, CAST(0x0000A54400B8CCA4 AS DateTime), N'Created product "Roddinton Chest Of Drawers"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (67, 1, CAST(0x0000A54400B8E64C AS DateTime), N'Created product "Hadelyn Chest Of Drawers"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (68, 1, CAST(0x0000A54400B92351 AS DateTime), N'Created product "Dexifield Chest Of Drawers"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (69, 1, CAST(0x0000A54400B9A10C AS DateTime), N'Created product "Larimer Dining Room TableZenfield Bedroom Bench"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (70, 1, CAST(0x0000A54400B9BF3B AS DateTime), N'Created product "North Shore Upholstered Bench"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (71, 1, CAST(0x0000A54400B9D422 AS DateTime), N'Created product "Moriann Bedroom Bench"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (72, 1, CAST(0x0000A54400B9F18E AS DateTime), N'Created product "Key Town Upholstered Bench"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (73, 1, CAST(0x0000A54400BA1691 AS DateTime), N'Created product "Ledelle Upholstered Bench"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (74, 1, CAST(0x0000A54400BA530E AS DateTime), N'Created product "Lacey Dining Room Table"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (75, 1, CAST(0x0000A54400BA834D AS DateTime), N'Created product "Demarlos Dining Room Table"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (76, 1, CAST(0x0000A54400BA9B16 AS DateTime), N'Created product "Glambrey Dining Room Table"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (77, 1, CAST(0x0000A54400BAC15C AS DateTime), N'Created product "Porter Dining Room Table"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (78, 1, CAST(0x0000A54400BAE151 AS DateTime), N'Created product "Owingsville Dining Room Table"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (79, 1, CAST(0x0000A54400BB5717 AS DateTime), N'Created product "Larimer Dining Room Chair"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (80, 1, CAST(0x0000A54400BB7725 AS DateTime), N'Created product "Trishelle Dining Room Chair"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (81, 1, CAST(0x0000A54400BB9DC9 AS DateTime), N'Created product "Lacey Dining Room Chair"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (82, 1, CAST(0x0000A54400BBB598 AS DateTime), N'Created product "Demarlos Dining Room Chair"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (83, 1, CAST(0x0000A54400BBCD3F AS DateTime), N'Created product "Chanella Dining Room Chair"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (84, 1, CAST(0x0000A54400BC0C09 AS DateTime), N'Created product "Berlmine Counter Height Barstool"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (85, 1, CAST(0x0000A54400BC21F6 AS DateTime), N'Created product "Bantilly Counter Height Barstool"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (86, 1, CAST(0x0000A54400BC41B6 AS DateTime), N'Created product "Pinnadel Pub Height Barstool"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (87, 1, CAST(0x0000A54400BC5AC5 AS DateTime), N'Created product "Owingsville Counter Height Barstool"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (88, 1, CAST(0x0000A54400BC8087 AS DateTime), N'Created product "Moriann Counter Height Barstool"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (89, 1, CAST(0x0000A54400BCB0DA AS DateTime), N'Created product "Birnalla Dining Room Server"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (90, 1, CAST(0x0000A54400BCD516 AS DateTime), N'Created product "Shallibay Dining Room Server"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (91, 1, CAST(0x0000A54400BD0736 AS DateTime), N'Created product "Krinden Dining Room Server"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (92, 1, CAST(0x0000A54400BD1F3C AS DateTime), N'Created product "Owingsville Dining Room Server"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (93, 1, CAST(0x0000A54400BD3980 AS DateTime), N'Created product "Tripton Dining Room Server"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (94, 1, CAST(0x0000A54400BD763F AS DateTime), N'Edited category "Desks"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (95, 1, CAST(0x0000A54400BDBD3C AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (96, 1, CAST(0x0000A54400BDD813 AS DateTime), N'Created product "Baraga 61" Home Office Desk"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (97, 1, CAST(0x0000A54400BDF171 AS DateTime), N'Created product "Carlyle 48" Home Office Desk"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (98, 1, CAST(0x0000A54400BE0607 AS DateTime), N'Created product "Hamlyn 60" Home Office Desk"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (99, 1, CAST(0x0000A54400BE2B84 AS DateTime), N'Created product "Gavelston 48" Home Office Desk"')
GO
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (100, 1, CAST(0x0000A54400BE41C3 AS DateTime), N'Created product "Tanshire 61" Home Office Desk"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (101, 1, CAST(0x0000A54400BF246D AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (102, 1, CAST(0x0000A54400C03ACC AS DateTime), N'Added new Customer "wholesale customers"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (103, 1, CAST(0x0000A54400C06D9D AS DateTime), N'Added new Customer "Retail clients"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (104, 1, CAST(0x0000A54400C148BF AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (105, 1, CAST(0x0000A54400C18E2D AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (106, 1, CAST(0x0000A54400C1C2E6 AS DateTime), N'Created bill #1')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (107, 1, CAST(0x0000A54400C1C315 AS DateTime), N'Edited product "Larimer Dining Room TableZenfield Bedroom Bench"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (108, 1, CAST(0x0000A54400C1C323 AS DateTime), N'Edited product "North Shore Upholstered Bench"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (109, 1, CAST(0x0000A54400C225E0 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (110, 1, CAST(0x0000A54400C2BFC0 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (111, 1, CAST(0x0000A54400C523B2 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (112, 1, CAST(0x0000A54400C575FB AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (113, 1, CAST(0x0000A54400C5B254 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (114, 1, CAST(0x0000A54400C66C28 AS DateTime), N'Created product "Birnalla 45" Home Office Desk"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (115, 1, CAST(0x0000A54400C8D1F8 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (116, 1, CAST(0x0000A54400D42A39 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (117, 1, CAST(0x0000A54400D550AF AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (118, 1, CAST(0x0000A54400D56A39 AS DateTime), N'Edited product "Larimer Dining Room TableZenfield Bedroom Bench"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (119, 1, CAST(0x0000A54400D5773D AS DateTime), N'Edited product "North Shore Upholstered Bench"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (120, 1, CAST(0x0000A54400D5A6CF AS DateTime), N'Created product "Birnalla 61" Home Office Desk"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (121, 1, CAST(0x0000A54400D5B6D7 AS DateTime), N'Edited product "Birnalla 61" Home Office Desk"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (122, 1, CAST(0x0000A54400D6CB0F AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (123, 1, CAST(0x0000A54400D7183E AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (124, 1, CAST(0x0000A54400D79586 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (125, 1, CAST(0x0000A54400D8D139 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (126, 1, CAST(0x0000A54400D9493B AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (127, 1, CAST(0x0000A54400D9B1E9 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (128, 1, CAST(0x0000A54400DAD927 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (129, 1, CAST(0x0000A54400DB4735 AS DateTime), N'Edited product "Kinlock Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (130, 1, CAST(0x0000A54400DC4E9D AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (131, 1, CAST(0x0000A54400E9BDB7 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (132, 1, CAST(0x0000A54400EA42DB AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (133, 1, CAST(0x0000A54400EADC33 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (134, 1, CAST(0x0000A54400EB3B35 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (135, 1, CAST(0x0000A54400F1D81E AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (136, 1, CAST(0x0000A54400F30CE0 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (137, 1, CAST(0x0000A54400F4F6E2 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (138, 1, CAST(0x0000A54400F51144 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (139, 1, CAST(0x0000A54400F53126 AS DateTime), N'Updated employee "Le Hai Nam"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (140, 1, CAST(0x0000A54400F56D59 AS DateTime), N'Edited role "User"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (141, 2, CAST(0x0000A54400F57BF4 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (142, 2, CAST(0x0000A54400F5CD66 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (143, 1, CAST(0x0000A54400F6819D AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (144, 2, CAST(0x0000A54400F68B28 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (145, 1, CAST(0x0000A54400F73924 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (146, 1, CAST(0x0000A54400F861B8 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (147, 2, CAST(0x0000A54400F8B4A9 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (148, 1, CAST(0x0000A54400FA03A0 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (149, 1, CAST(0x0000A54400FBBC7F AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (150, 1, CAST(0x0000A54400FC63A4 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (151, 1, CAST(0x0000A54400FCC29D AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (152, 3, CAST(0x0000A54401008266 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (153, 1, CAST(0x0000A5440101E235 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (154, 1, CAST(0x0000A5440102B8AB AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (155, 1, CAST(0x0000A5440103EC65 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (156, 1, CAST(0x0000A54401043E97 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (157, 1, CAST(0x0000A5440104591D AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (158, 1, CAST(0x0000A5440163A2F4 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (159, 1, CAST(0x0000A54500F07097 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (160, 1, CAST(0x0000A54500F6A5FF AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (161, 1, CAST(0x0000A54500F7972E AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (162, 1, CAST(0x0000A54500F8368F AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (163, 1, CAST(0x0000A54500F8FF68 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (164, 1, CAST(0x0000A54500F941C4 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (165, 1, CAST(0x0000A54500FA15CB AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (166, 1, CAST(0x0000A54500FA703B AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (167, 1, CAST(0x0000A54500FAFAD7 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (168, 1, CAST(0x0000A54500FBF3EC AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (169, 1, CAST(0x0000A54500FC9A35 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (170, 1, CAST(0x0000A54500FCE7D9 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (171, 1, CAST(0x0000A54500FE1797 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (172, 1, CAST(0x0000A54500FEA0E8 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (173, 1, CAST(0x0000A5450100B8F4 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (174, 1, CAST(0x0000A5450101B369 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (175, 1, CAST(0x0000A54501047958 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (176, 1, CAST(0x0000A5450105B390 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (177, 1, CAST(0x0000A545010ACF42 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (178, 1, CAST(0x0000A545010B3D6C AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (179, 1, CAST(0x0000A545010D6981 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (180, 1, CAST(0x0000A545010E409A AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (181, 1, CAST(0x0000A54501130612 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (182, 1, CAST(0x0000A5450113AC5F AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (183, 1, CAST(0x0000A5450113EB9A AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (184, 1, CAST(0x0000A54501143B06 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (185, 1, CAST(0x0000A5450114D942 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (186, 1, CAST(0x0000A54501276B85 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (187, 1, CAST(0x0000A545012985F7 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (188, 1, CAST(0x0000A5450129A0E1 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (189, 1, CAST(0x0000A545012A199D AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (190, 1, CAST(0x0000A545012A608C AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (191, 1, CAST(0x0000A545012C8BFF AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (192, 1, CAST(0x0000A54501312181 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (193, 1, CAST(0x0000A5450132240E AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (194, 1, CAST(0x0000A545013279E3 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (195, 1, CAST(0x0000A545014F706B AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (196, 1, CAST(0x0000A545014FBFA2 AS DateTime), N'Added new employee "Nguyen Thi Bao An"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (197, 1, CAST(0x0000A545014FE2B9 AS DateTime), N'Added new employee "Phan Duc Hai"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (198, 1, CAST(0x0000A5450150EAA1 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (199, 1, CAST(0x0000A54501511427 AS DateTime), N'Created bill #2')
GO
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (200, 1, CAST(0x0000A54501511520 AS DateTime), N'Edited product "Darcy Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (201, 1, CAST(0x0000A545015115BF AS DateTime), N'Edited product "Kinlock Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (202, 1, CAST(0x0000A545015115D3 AS DateTime), N'Edited product "Gypsum Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (203, 1, CAST(0x0000A545015115E1 AS DateTime), N'Edited product "Hodan Sofa Chaise"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (204, 1, CAST(0x0000A545015115FF AS DateTime), N'Edited product "Hodan Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (205, 1, CAST(0x0000A54501511612 AS DateTime), N'Edited product "Stringer Reclining Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (206, 1, CAST(0x0000A54501511627 AS DateTime), N'Edited product "Ballari Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (207, 1, CAST(0x0000A54501511657 AS DateTime), N'Edited product "Geordie Sofa Chaise"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (208, 1, CAST(0x0000A54501511674 AS DateTime), N'Edited product "Hariston Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (209, 1, CAST(0x0000A54501511688 AS DateTime), N'Edited product "Patola Park 4-Piece Sectional"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (210, 1, CAST(0x0000A54501511691 AS DateTime), N'Edited product "Loric 3-Piece Sectional"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (211, 1, CAST(0x0000A54501517BA6 AS DateTime), N'Edited Customer "Vo Thanh Son"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (212, 1, CAST(0x0000A54501518676 AS DateTime), N'Edited Customer "Luong Thi Phuong Thao"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (213, 1, CAST(0x0000A54501519F79 AS DateTime), N'Created bill #3')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (214, 1, CAST(0x0000A54501519F87 AS DateTime), N'Edited product "Darcy Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (215, 1, CAST(0x0000A54501519F97 AS DateTime), N'Edited product "Hariston Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (216, 1, CAST(0x0000A54501519F9F AS DateTime), N'Edited product "Stringer Reclining Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (217, 1, CAST(0x0000A54501519FB1 AS DateTime), N'Edited product "Ballari Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (218, 1, CAST(0x0000A54501519FCF AS DateTime), N'Edited product "Geordie Sofa Chaise"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (219, 1, CAST(0x0000A54501519FF5 AS DateTime), N'Edited product "Kinlock Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (220, 1, CAST(0x0000A5450151A016 AS DateTime), N'Edited product "Damacio 6-Piece Sectional With Power"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (221, 1, CAST(0x0000A5450151A040 AS DateTime), N'Edited product "Pierin Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (222, 1, CAST(0x0000A5450151C19D AS DateTime), N'Created bill #4')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (223, 1, CAST(0x0000A5450151C1AC AS DateTime), N'Edited product "Stringer Reclining Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (224, 1, CAST(0x0000A5450151C1B5 AS DateTime), N'Edited product "Kinlock Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (225, 1, CAST(0x0000A5450151C1C1 AS DateTime), N'Edited product "Hodan Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (226, 1, CAST(0x0000A5450151C1CA AS DateTime), N'Edited product "Darcy Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (227, 1, CAST(0x0000A5450151C1D3 AS DateTime), N'Edited product "Patola Park 4-Piece Sectional"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (228, 1, CAST(0x0000A5450151C1DC AS DateTime), N'Edited product "Damacio 6-Piece Sectional With Power"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (229, 1, CAST(0x0000A5450151C1E5 AS DateTime), N'Edited product "Ballari Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (230, 1, CAST(0x0000A5450151C1EB AS DateTime), N'Edited product "Loric 3-Piece Sectional"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (231, 1, CAST(0x0000A5450151C1F1 AS DateTime), N'Edited product "Jessa Place 3-Piece Sectional"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (232, 1, CAST(0x0000A5450151DE1D AS DateTime), N'Created bill #5')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (233, 1, CAST(0x0000A5450151DE26 AS DateTime), N'Edited product "Kinlock Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (234, 1, CAST(0x0000A5450151DE2C AS DateTime), N'Edited product "Hariston Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (235, 1, CAST(0x0000A5450151DE32 AS DateTime), N'Edited product "Darcy Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (236, 1, CAST(0x0000A5450151DE35 AS DateTime), N'Edited product "Hodan Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (237, 1, CAST(0x0000A5450151DE3B AS DateTime), N'Edited product "Stringer Reclining Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (238, 1, CAST(0x0000A5450151DE41 AS DateTime), N'Edited product "Pierin Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (239, 1, CAST(0x0000A5450151DE47 AS DateTime), N'Edited product "Ballari Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (240, 1, CAST(0x0000A5450151DE4D AS DateTime), N'Edited product "Geordie Sofa Chaise"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (241, 1, CAST(0x0000A5450151DE50 AS DateTime), N'Edited product "Patola Park 4-Piece Sectional"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (242, 1, CAST(0x0000A5450151DE56 AS DateTime), N'Edited product "Damacio 6-Piece Sectional With Power"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (243, 1, CAST(0x0000A5450151DE59 AS DateTime), N'Edited product "Loric 3-Piece Sectional"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (244, 1, CAST(0x0000A5450151DE62 AS DateTime), N'Edited product "Jessa Place 3-Piece Sectional"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (245, 1, CAST(0x0000A5450151DE68 AS DateTime), N'Edited product "Alliston DuraBlend® Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (246, 1, CAST(0x0000A5450151FA9A AS DateTime), N'Created bill #6')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (247, 1, CAST(0x0000A5450151FAA0 AS DateTime), N'Edited product "McNeil Power Recliner"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (248, 1, CAST(0x0000A5450151FAA6 AS DateTime), N'Edited product "Exhilaration Power Reclining Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (249, 1, CAST(0x0000A5450151FAAC AS DateTime), N'Edited product "Beadle Power Recliner"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (250, 1, CAST(0x0000A5450151FAB2 AS DateTime), N'Edited product "Porter Queen Panel Bed"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (251, 1, CAST(0x0000A5450151FABB AS DateTime), N'Edited product "Oberson Power Reclining Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (252, 1, CAST(0x0000A5450151FAC4 AS DateTime), N'Edited product "Darshmore Power Recliner"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (253, 1, CAST(0x0000A5450151FB06 AS DateTime), N'Edited product "Dinelli Queen Sofa Sleeper"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (254, 1, CAST(0x0000A54501520D47 AS DateTime), N'Created bill #7')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (255, 1, CAST(0x0000A54501520D50 AS DateTime), N'Edited product "Ballari Queen Sofa Sleeper"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (256, 1, CAST(0x0000A54501520D68 AS DateTime), N'Edited product "Zeth Twin Sofa Sleeper"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (257, 1, CAST(0x0000A54501520D6B AS DateTime), N'Edited product "Corley Queen Sofa Sleeper"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (258, 1, CAST(0x0000A54501520D71 AS DateTime), N'Edited product "Oberson Power Reclining Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (259, 1, CAST(0x0000A54501520D74 AS DateTime), N'Edited product "Darshmore Power Recliner"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (260, 1, CAST(0x0000A54501520D7A AS DateTime), N'Edited product "Porter Queen Panel Bed"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (261, 1, CAST(0x0000A54501520D7D AS DateTime), N'Edited product "Corraya Queen Panel Bed"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (262, 1, CAST(0x0000A54501520D83 AS DateTime), N'Edited product "Prentice Queen Panel Bed"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (263, 1, CAST(0x0000A54501520D86 AS DateTime), N'Edited product "Wesling Queen Panel Bed"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (264, 1, CAST(0x0000A54501522F4B AS DateTime), N'Created bill #8')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (265, 1, CAST(0x0000A54501522F54 AS DateTime), N'Edited product "Stringer Reclining Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (266, 1, CAST(0x0000A54501522F5F AS DateTime), N'Edited product "Pierin Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (267, 1, CAST(0x0000A54501522F66 AS DateTime), N'Edited product "Ballari Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (268, 1, CAST(0x0000A54501522F6F AS DateTime), N'Edited product "Geordie Sofa Chaise"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (269, 1, CAST(0x0000A54501522F7B AS DateTime), N'Edited product "Patola Park 4-Piece Sectional"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (270, 1, CAST(0x0000A54501522F7F AS DateTime), N'Edited product "Damacio 6-Piece Sectional With Power"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (271, 1, CAST(0x0000A54501522F85 AS DateTime), N'Edited product "Loric 3-Piece Sectional"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (272, 1, CAST(0x0000A54501522F8B AS DateTime), N'Edited product "Jessa Place 3-Piece Sectional"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (273, 1, CAST(0x0000A54501522F93 AS DateTime), N'Edited product "Alliston DuraBlend® Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (274, 1, CAST(0x0000A545015243D2 AS DateTime), N'Created bill #9')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (275, 1, CAST(0x0000A545015243D8 AS DateTime), N'Edited product "Hodan Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (276, 1, CAST(0x0000A545015243E1 AS DateTime), N'Edited product "Stringer Reclining Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (277, 1, CAST(0x0000A545015243EA AS DateTime), N'Edited product "Pierin Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (278, 1, CAST(0x0000A545015243F0 AS DateTime), N'Edited product "Ballari Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (279, 1, CAST(0x0000A545015243F6 AS DateTime), N'Edited product "Geordie Sofa Chaise"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (280, 1, CAST(0x0000A545015243FC AS DateTime), N'Edited product "Patola Park 4-Piece Sectional"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (281, 1, CAST(0x0000A545015243FF AS DateTime), N'Edited product "Damacio 6-Piece Sectional With Power"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (282, 1, CAST(0x0000A54501524408 AS DateTime), N'Edited product "Loric 3-Piece Sectional"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (283, 1, CAST(0x0000A54501524415 AS DateTime), N'Edited product "Jessa Place 3-Piece Sectional"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (284, 1, CAST(0x0000A54501524421 AS DateTime), N'Edited product "Alliston DuraBlend® Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (285, 1, CAST(0x0000A5450152442A AS DateTime), N'Edited product "Corley Queen Sofa Sleeper"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (286, 1, CAST(0x0000A54501524436 AS DateTime), N'Edited product "Zeth Twin Sofa Sleeper"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (287, 1, CAST(0x0000A5450152443D AS DateTime), N'Edited product "Ballari Queen Sofa Sleeper"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (288, 1, CAST(0x0000A545015257F7 AS DateTime), N'Created bill #10')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (289, 1, CAST(0x0000A54501525800 AS DateTime), N'Edited product "Jessa Place 3-Piece Sectional"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (290, 1, CAST(0x0000A54501525806 AS DateTime), N'Edited product "Corley Queen Sofa Sleeper"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (291, 1, CAST(0x0000A54501525809 AS DateTime), N'Edited product "Zeth Twin Sofa Sleeper"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (292, 1, CAST(0x0000A5450152580F AS DateTime), N'Edited product "Ballari Queen Sofa Sleeper"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (293, 1, CAST(0x0000A54501525812 AS DateTime), N'Edited product "Damacio 6-Piece Sectional With Power"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (294, 1, CAST(0x0000A54501525818 AS DateTime), N'Edited product "Pierin Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (295, 1, CAST(0x0000A5450152581B AS DateTime), N'Edited product "Hodan Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (296, 1, CAST(0x0000A54501525821 AS DateTime), N'Edited product "Stringer Reclining Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (297, 1, CAST(0x0000A54501525824 AS DateTime), N'Edited product "Ballari Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (298, 1, CAST(0x0000A5450152582A AS DateTime), N'Edited product "Patola Park 4-Piece Sectional"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (299, 1, CAST(0x0000A54501528686 AS DateTime), N'Created bill #11')
GO
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (300, 1, CAST(0x0000A54501528692 AS DateTime), N'Edited product "Ballari Queen Sofa Sleeper"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (301, 1, CAST(0x0000A5450152869E AS DateTime), N'Edited product "Jessa Place 3-Piece Sectional"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (302, 1, CAST(0x0000A545015286AA AS DateTime), N'Edited product "Zeth Twin Sofa Sleeper"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (303, 1, CAST(0x0000A545015286BA AS DateTime), N'Edited product "Oberson Power Reclining Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (304, 1, CAST(0x0000A545015286C8 AS DateTime), N'Edited product "Exhilaration Power Reclining Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (305, 1, CAST(0x0000A545015286D5 AS DateTime), N'Edited product "Porter Queen Panel Bed"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (306, 1, CAST(0x0000A545015286DB AS DateTime), N'Edited product "Darshmore Power Recliner"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (307, 1, CAST(0x0000A54501528733 AS DateTime), N'Edited product "Corraya Queen Panel Bed"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (308, 1, CAST(0x0000A54501528763 AS DateTime), N'Edited product "Larimer Dining Room TableZenfield Bedroom Bench"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (309, 1, CAST(0x0000A54501528790 AS DateTime), N'Edited product "Fanzere Chest Of Drawers"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (310, 1, CAST(0x0000A5450152879E AS DateTime), N'Edited product "Kaslyn Chest Of Drawers"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (311, 1, CAST(0x0000A545015287A6 AS DateTime), N'Edited product "Harlinton Dresser And Mirror"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (312, 1, CAST(0x0000A545015287B5 AS DateTime), N'Edited product "Roddinton Chest Of Drawers"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (313, 1, CAST(0x0000A545015287BE AS DateTime), N'Edited product "Lacey Dining Room Table"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (314, 1, CAST(0x0000A545015287C4 AS DateTime), N'Edited product "Moriann Bedroom Bench"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (315, 1, CAST(0x0000A5450152A49D AS DateTime), N'Created bill #12')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (316, 1, CAST(0x0000A5450152A4AC AS DateTime), N'Edited product "Gavelston 48" Home Office Desk"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (317, 1, CAST(0x0000A5450152A4B2 AS DateTime), N'Edited product "Tanshire 61" Home Office Desk"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (318, 1, CAST(0x0000A5450152A4B8 AS DateTime), N'Edited product "Carlyle 48" Home Office Desk"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (319, 1, CAST(0x0000A5450152A4C1 AS DateTime), N'Edited product "Tripton Dining Room Server"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (320, 1, CAST(0x0000A5450152A4C7 AS DateTime), N'Edited product "Owingsville Dining Room Server"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (321, 1, CAST(0x0000A5450152A4CD AS DateTime), N'Edited product "Krinden Dining Room Server"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (322, 1, CAST(0x0000A5450152A4D6 AS DateTime), N'Edited product "Birnalla Dining Room Server"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (323, 1, CAST(0x0000A5450152A4D9 AS DateTime), N'Edited product "Shallibay Dining Room Server"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (324, 1, CAST(0x0000A5450152A4E2 AS DateTime), N'Edited product "Moriann Counter Height Barstool"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (325, 1, CAST(0x0000A5450152A4E8 AS DateTime), N'Edited product "Owingsville Counter Height Barstool"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (326, 1, CAST(0x0000A5450152A4EB AS DateTime), N'Edited product "Pinnadel Pub Height Barstool"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (327, 1, CAST(0x0000A5450152A4F1 AS DateTime), N'Edited product "Bantilly Counter Height Barstool"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (328, 1, CAST(0x0000A5450152CBAA AS DateTime), N'Added new employee "Vo Thanh Son"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (329, 1, CAST(0x0000A5450152D8A2 AS DateTime), N'Updated employee "Thai Thanh Nam"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (330, 1, CAST(0x0000A5450153200C AS DateTime), N'Added new employee "Nguyen Thai Bao"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (331, 1, CAST(0x0000A545015349A7 AS DateTime), N'Added new employee "Phan Dinh Duong"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (332, 1, CAST(0x0000A54501538057 AS DateTime), N'Added new employee "Bui Thong Hai Thien"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (333, 1, CAST(0x0000A5450153AE2F AS DateTime), N'Added new employee "Le Duc Minh"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (334, 1, CAST(0x0000A5450153B859 AS DateTime), N'Updated employee "Nguyen Thi Bao An"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (335, 1, CAST(0x0000A5450153C26A AS DateTime), N'Updated employee "Phan Duc Hai"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (336, 1, CAST(0x0000A5450153CE53 AS DateTime), N'Updated employee "Le Hai Nam"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (337, 1, CAST(0x0000A545015ED8EE AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (338, 1, CAST(0x0000A545016038C4 AS DateTime), N'Added new Customer "Phan Hoang Hai"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (339, 1, CAST(0x0000A545016067B9 AS DateTime), N'Added new Customer "Amelia Lovegood"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (340, 1, CAST(0x0000A5450160DD41 AS DateTime), N'Added new Customer "Melisa Bush"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (341, 1, CAST(0x0000A545016102A2 AS DateTime), N'Added new Customer "Duong Tuan"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (342, 1, CAST(0x0000A5450162762E AS DateTime), N'Created bill #13')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (343, 1, CAST(0x0000A54501627670 AS DateTime), N'Edited product "Pierin Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (344, 1, CAST(0x0000A5450162767E AS DateTime), N'Edited product "Darcy Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (345, 1, CAST(0x0000A54501627689 AS DateTime), N'Edited product "Ballari Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (346, 1, CAST(0x0000A54501627694 AS DateTime), N'Edited product "Stringer Reclining Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (347, 1, CAST(0x0000A5450162769D AS DateTime), N'Edited product "Gypsum Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (348, 1, CAST(0x0000A545016276A6 AS DateTime), N'Edited product "Loric 3-Piece Sectional"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (349, 1, CAST(0x0000A546008774CF AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (350, 1, CAST(0x0000A5460087AC10 AS DateTime), N'Created bill #14')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (351, 1, CAST(0x0000A5460087AC51 AS DateTime), N'Edited product "Geordie Sofa Chaise"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (352, 1, CAST(0x0000A54600886CBC AS DateTime), N'Created bill #15')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (353, 1, CAST(0x0000A54600886CCA AS DateTime), N'Edited product "Darcy Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (354, 1, CAST(0x0000A5460088F713 AS DateTime), N'Created bill #16')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (355, 1, CAST(0x0000A5460088F71E AS DateTime), N'Edited product "Hariston Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (356, 1, CAST(0x0000A546008934B1 AS DateTime), N'Edited product "Hariston Sofa"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (357, 1, CAST(0x0000A546008B1346 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (358, 1, CAST(0x0000A546008B25D1 AS DateTime), N'Edited product "Darcy Loveseat"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (359, 1, CAST(0x0000A54600EC022A AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (360, 1, CAST(0x0000A54600EC11CD AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (361, 1, CAST(0x0000A54600EC1F0F AS DateTime), N'Updated employee "Nguyen Thi Bao An"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (362, 4, CAST(0x0000A54600EC2E7A AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (363, 4, CAST(0x0000A54600EC7EBA AS DateTime), N'Updated employee "Nguyen Thai Bao"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (364, 4, CAST(0x0000A54600EC8D64 AS DateTime), N'Updated employee "Nguyen Thai Bao"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (365, 7, CAST(0x0000A54600EC96E2 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (366, 1, CAST(0x0000A54600F9EC92 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (367, 7, CAST(0x0000A54600F9FEAE AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (368, 7, CAST(0x0000A54600FA08FE AS DateTime), N'Updated employee "Nguyen Thai Bao"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (369, 7, CAST(0x0000A54600FA2424 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (370, 1, CAST(0x0000A54600FCFCE6 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (371, 1, CAST(0x0000A54600FD1513 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (372, 1, CAST(0x0000A54600FD29CF AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (373, 1, CAST(0x0000A54600FD37C4 AS DateTime), N'Edited role "Admin"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (374, 1, CAST(0x0000A54600FD4454 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (375, 1, CAST(0x0000A54600FD78DA AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (376, 1, CAST(0x0000A54600FDFE05 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (377, 1, CAST(0x0000A54600FE9DF0 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (378, 1, CAST(0x0000A54600FEB7A6 AS DateTime), N'Edited role "Admin"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (379, 1, CAST(0x0000A54600FED03A AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (380, 1, CAST(0x0000A54600FF7D9A AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (381, 7, CAST(0x0000A54600FF94CD AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (382, 1, CAST(0x0000A5460116ABFB AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (383, 1, CAST(0x0000A5460116E381 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (384, 1, CAST(0x0000A54601172AF2 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (385, 1, CAST(0x0000A5460117AF96 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (386, 1, CAST(0x0000A5460117F209 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (387, 1, CAST(0x0000A54601187B03 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (388, 1, CAST(0x0000A54601189EE2 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (389, 1, CAST(0x0000A5460118C1CC AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (390, 1, CAST(0x0000A54601212EC0 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (391, 1, CAST(0x0000A54601214F2A AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (392, 1, CAST(0x0000A54601217525 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (393, 1, CAST(0x0000A5460122804A AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (394, 1, CAST(0x0000A5460122D06A AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (395, 1, CAST(0x0000A54601234F99 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (396, 1, CAST(0x0000A546016E11D2 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (397, 1, CAST(0x0000A546016E6902 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (398, 1, CAST(0x0000A54700131818 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (399, 1, CAST(0x0000A5470013513F AS DateTime), N'Logged in to the system.')
GO
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (400, 1, CAST(0x0000A547001394DA AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (401, 1, CAST(0x0000A547001479D2 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (402, 1, CAST(0x0000A54700163ED5 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (403, 1, CAST(0x0000A5470021CF7A AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (404, 1, CAST(0x0000A5470023097D AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (405, 1, CAST(0x0000A547002316EC AS DateTime), N'Updated employee "administrator"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (406, 1, CAST(0x0000A54700234EF8 AS DateTime), N'Added new employee "user"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (407, 1, CAST(0x0000A54700237C74 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (408, 11, CAST(0x0000A54700238517 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (409, 1, CAST(0x0000A5470023C72C AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (410, 1, CAST(0x0000A5470024D31D AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (411, 1, CAST(0x0000A5470024DFDB AS DateTime), N'Edited role "Admin"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (412, 1, CAST(0x0000A5470024ED26 AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (413, 1, CAST(0x0000A5470024F94B AS DateTime), N'Edited role "Admin"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (414, 1, CAST(0x0000A54700250811 AS DateTime), N'Edited role "Admin"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (415, 1, CAST(0x0000A5470025184E AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (416, 1, CAST(0x0000A54700258A2B AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (417, 1, CAST(0x0000A54700259B87 AS DateTime), N'Edited role "Admin"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (418, 1, CAST(0x0000A5470025B2EB AS DateTime), N'Logged in to the system.')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (419, 1, CAST(0x0000A5470025C5CE AS DateTime), N'Edited role "Admin"')
INSERT [dbo].[HAM_Diary] ([ID], [AccountID], [Time], [Description]) VALUES (420, 1, CAST(0x0000A547002645A8 AS DateTime), N'Logged in to the system.')
SET IDENTITY_INSERT [dbo].[HAM_Diary] OFF
SET IDENTITY_INSERT [dbo].[HAM_Employee] ON 

INSERT [dbo].[HAM_Employee] ([ID], [Name], [Address], [ContactNo], [Username], [Password], [Department], [RoleID], [IsActive]) VALUES (1, N'administrator', N'123 GreenWood St', N'0909212123', N'admin', N'21232f297a57a5a743894ae4a801fc3', N'admin', 2, 1)
INSERT [dbo].[HAM_Employee] ([ID], [Name], [Address], [ContactNo], [Username], [Password], [Department], [RoleID], [IsActive]) VALUES (2, N'Le Hai Nam', N'123 Nguyen Du', N'0123452974', N'hnam', N'cc175b9c0f1b6a831c399e269772661', N'staff', 2, 1)
INSERT [dbo].[HAM_Employee] ([ID], [Name], [Address], [ContactNo], [Username], [Password], [Department], [RoleID], [IsActive]) VALUES (3, N'Thai Thanh Nam', N'123 An Cuu', N'0123456789', N'tnam', N'cc175b9c0f1b6a831c399e269772661', N'staff', 2, 1)
INSERT [dbo].[HAM_Employee] ([ID], [Name], [Address], [ContactNo], [Username], [Password], [Department], [RoleID], [IsActive]) VALUES (4, N'Nguyen Thi Bao An', N'123 Dien Bien Phu', N'0121456578', N'ban', N'4317652e7e8e3edc6d6e67122d750', N'staff', 2, 1)
INSERT [dbo].[HAM_Employee] ([ID], [Name], [Address], [ContactNo], [Username], [Password], [Department], [RoleID], [IsActive]) VALUES (5, N'Phan Duc Hai', N'123 Ngu Binh', N'0121456579', N'dhai', N'cc175b9c0f1b6a831c399e269772661', N'staff', 2, 1)
INSERT [dbo].[HAM_Employee] ([ID], [Name], [Address], [ContactNo], [Username], [Password], [Department], [RoleID], [IsActive]) VALUES (6, N'Vo Thanh Son', N'123 Thuy Duong', N'0123458927', N'vtson', N'cc175b9c0f1b6a831c399e269772661', N'admin', 2, 1)
INSERT [dbo].[HAM_Employee] ([ID], [Name], [Address], [ContactNo], [Username], [Password], [Department], [RoleID], [IsActive]) VALUES (7, N'Nguyen Thai Bao', N'123 Xa Lo Dai Hang', N'0123456789', N'ntbao', N'7337e2f117b38edd90ef8ddd50c3146', N'staff', 1, 1)
INSERT [dbo].[HAM_Employee] ([ID], [Name], [Address], [ContactNo], [Username], [Password], [Department], [RoleID], [IsActive]) VALUES (8, N'Phan Dinh Duong', N'321 Ngu Binh', N'0123456782', N'pdduong', N'cc175b9c0f1b6a831c399e269772661', N'staff', 1, 1)
INSERT [dbo].[HAM_Employee] ([ID], [Name], [Address], [ContactNo], [Username], [Password], [Department], [RoleID], [IsActive]) VALUES (9, N'Bui Thong Hai Thien', N'312 Dat Hue', N'0123456897', N'bththien', N'cc175b9c0f1b6a831c399e269772661', N'staff', 1, 1)
INSERT [dbo].[HAM_Employee] ([ID], [Name], [Address], [ContactNo], [Username], [Password], [Department], [RoleID], [IsActive]) VALUES (10, N'Le Duc Minh', N'321 Ngu Binh', N'0123467894', N'ldminh', N'cc175b9c0f1b6a831c399e269772661', N'staff', 1, 1)
INSERT [dbo].[HAM_Employee] ([ID], [Name], [Address], [ContactNo], [Username], [Password], [Department], [RoleID], [IsActive]) VALUES (11, N'user', N'123 GreenWood St', N'0909546468', N'user', N'ee11cbb19052e4b7aac0ca6c23ee', N'staff', 1, 1)
SET IDENTITY_INSERT [dbo].[HAM_Employee] OFF
SET IDENTITY_INSERT [dbo].[HAM_OrderLine] ON 

INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (1, 1, 46, 1, CAST(588.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (2, 1, 47, 1, CAST(499.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (3, 2, 1, 2, CAST(600.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (4, 2, 4, 2, CAST(500.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (5, 2, 2, 2, CAST(600.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (6, 2, 3, 2, CAST(750.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (7, 2, 7, 6, CAST(700.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (8, 2, 8, 2, CAST(688.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (9, 2, 10, 2, CAST(900.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (10, 2, 11, 1, CAST(495.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (11, 2, 5, 2, CAST(688.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (12, 2, 12, 2, CAST(1388.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (13, 2, 14, 2, CAST(2350.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (14, 3, 6, 1, CAST(1000.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (15, 3, 5, 2, CAST(688.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (16, 3, 8, 2, CAST(688.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (17, 3, 10, 1, CAST(900.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (18, 3, 11, 1, CAST(495.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (19, 3, 4, 1, CAST(500.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (20, 3, 13, 2, CAST(3488.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (21, 3, 9, 1, CAST(1200.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (22, 4, 8, 2, CAST(688.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (23, 4, 4, 1, CAST(500.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (24, 4, 7, 1, CAST(700.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (25, 4, 1, 1, CAST(600.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (26, 4, 12, 2, CAST(1388.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (27, 4, 13, 1, CAST(3488.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (28, 4, 10, 2, CAST(900.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (29, 4, 14, 1, CAST(2350.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (30, 4, 15, 1, CAST(1388.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (31, 5, 4, 1, CAST(500.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (32, 5, 5, 1, CAST(688.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (33, 5, 6, 1, CAST(1000.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (34, 5, 7, 1, CAST(700.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (35, 5, 8, 1, CAST(688.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (36, 5, 9, 1, CAST(1200.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (37, 5, 10, 1, CAST(900.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (38, 5, 11, 1, CAST(495.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (39, 5, 12, 1, CAST(1388.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (40, 5, 13, 1, CAST(3488.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (41, 5, 14, 1, CAST(2350.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (42, 5, 15, 1, CAST(1388.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (43, 5, 16, 1, CAST(1388.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (44, 6, 22, 3, CAST(350.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (45, 6, 23, 2, CAST(700.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (46, 6, 25, 2, CAST(800.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (47, 6, 26, 2, CAST(288.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (48, 6, 21, 2, CAST(3800.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (49, 6, 24, 1, CAST(1500.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (50, 6, 20, 1, CAST(1999.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (51, 7, 19, 1, CAST(1488.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (52, 7, 18, 1, CAST(2388.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (53, 7, 17, 1, CAST(1388.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (54, 7, 21, 1, CAST(3800.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (55, 7, 24, 1, CAST(1500.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (56, 7, 26, 1, CAST(288.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (57, 7, 27, 1, CAST(1000.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (58, 7, 28, 3, CAST(700.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (59, 7, 29, 1, CAST(588.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (60, 8, 8, 1, CAST(688.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (61, 8, 9, 1, CAST(1200.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (62, 8, 10, 1, CAST(900.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (63, 8, 11, 1, CAST(495.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (64, 8, 12, 1, CAST(1388.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (65, 8, 13, 1, CAST(3488.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (66, 8, 14, 1, CAST(2350.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (67, 8, 15, 1, CAST(1388.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (68, 8, 16, 1, CAST(1388.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (69, 9, 7, 1, CAST(700.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (70, 9, 8, 1, CAST(688.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (71, 9, 9, 1, CAST(1200.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (72, 9, 10, 1, CAST(900.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (73, 9, 11, 1, CAST(495.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (74, 9, 12, 1, CAST(1388.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (75, 9, 13, 1, CAST(3488.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (76, 9, 14, 1, CAST(2350.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (77, 9, 15, 1, CAST(1388.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (78, 9, 16, 1, CAST(1388.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (79, 9, 17, 1, CAST(1388.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (80, 9, 18, 1, CAST(2388.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (81, 9, 19, 1, CAST(1488.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (82, 10, 15, 2, CAST(1388.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (83, 10, 17, 3, CAST(1388.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (84, 10, 18, 1, CAST(2388.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (85, 10, 19, 2, CAST(1488.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (86, 10, 13, 1, CAST(3488.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (87, 10, 9, 2, CAST(1200.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (88, 10, 7, 1, CAST(700.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (89, 10, 8, 1, CAST(688.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (90, 10, 10, 1, CAST(900.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (91, 10, 12, 1, CAST(1388.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (92, 11, 19, 1, CAST(1488.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (93, 11, 15, 2, CAST(1388.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (94, 11, 18, 3, CAST(2388.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (95, 11, 21, 1, CAST(3800.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (96, 11, 23, 1, CAST(700.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (97, 11, 26, 1, CAST(288.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (98, 11, 24, 1, CAST(1500.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (99, 11, 27, 1, CAST(1000.00 AS Decimal(18, 2)))
GO
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (100, 11, 46, 1, CAST(588.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (101, 11, 42, 1, CAST(488.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (102, 11, 41, 1, CAST(522.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (103, 11, 39, 1, CAST(355.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (104, 11, 43, 1, CAST(488.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (105, 11, 51, 2, CAST(288.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (106, 11, 48, 1, CAST(225.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (107, 12, 74, 2, CAST(399.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (108, 12, 75, 1, CAST(599.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (109, 12, 72, 1, CAST(299.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (110, 12, 70, 1, CAST(599.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (111, 12, 69, 1, CAST(499.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (112, 12, 68, 1, CAST(799.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (113, 12, 66, 2, CAST(699.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (114, 12, 67, 1, CAST(599.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (115, 12, 65, 1, CAST(65.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (116, 12, 64, 1, CAST(75.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (117, 12, 63, 1, CAST(125.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (118, 12, 62, 1, CAST(33.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (119, 13, 9, 3, CAST(1200.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (120, 13, 6, 1, CAST(1000.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (121, 13, 10, 1, CAST(900.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (122, 13, 8, 5, CAST(688.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (123, 13, 2, 1, CAST(600.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (124, 13, 14, 1, CAST(2350.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (125, 14, 11, 1, CAST(495.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (126, 15, 6, 97, CAST(1000.00 AS Decimal(18, 2)))
INSERT [dbo].[HAM_OrderLine] ([ID], [OrderID], [ProductID], [Quantity], [ActualSalePrice]) VALUES (127, 16, 5, 95, CAST(688.00 AS Decimal(18, 2)))
SET IDENTITY_INSERT [dbo].[HAM_OrderLine] OFF
SET IDENTITY_INSERT [dbo].[HAM_Product] ON 

INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (1, N'Darcy Sofa', N'Talk about fine lines and great curves. That’s the beauty of the Darcy sofa—made to suit your appreciation for clean, contemporary style. A striking flared frame, comfy pillow top armrests and an ultra-soft upholstery that holds up to everyday living complete this fashion statement.', CAST(600.00 AS Decimal(18, 2)), 97, 1, CAST(550.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (2, N'Gypsum Sofa', N'Such a chic ensemble, this beautifully tailored piece was inspired by fashionable menswear. What looks like a “gray flannel suit” suits the Gypsum sofa wonderfully well. We especially love the look of low-profile arms with a subtly sloped flair. Tweed-like upholstery—so soft and comfortable to the touch—suits so many styles perfectly.', CAST(600.00 AS Decimal(18, 2)), 97, 1, CAST(555.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (3, N'Hodan Sofa Chaise', N'Sometimes clean and simple is simply beautiful to behold. That’s the case with the Hodan sofa chaise. It’s contemporary—yet warm and inviting—in a soft, earthy shade with plush nap upholstery. Speaking of naps, lay back and enjoy the chaise. With a moveable ottoman-style base and reversible seat cushion, you can have it on the right or the left.', CAST(750.00 AS Decimal(18, 2)), 98, 1, CAST(710.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (4, N'Kinlock Sofa', N'Dreamland is just a seat away with the modern Kinlock sofa. The minute you settle in, cushiony armrests and chair back reach out and comfort you from head to toe. Nailhead accents add a beautiful punctuation to the curved frame.', CAST(500.00 AS Decimal(18, 2)), 95, 1, CAST(400.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (5, N'Hariston Sofa', N'A refreshingly modern twist on European tradition. Hariston sofa beautifully goes with the flow with its distinctively contoured and set-back Charles of London arms, tastefully tailored with pretty pleating. Accent pillows in bright florals, bold stripes and handsome plaid mix it up in style.', CAST(688.00 AS Decimal(18, 2)), 66, 1, CAST(600.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (6, N'Darcy Loveseat', N'Talk about fine lines and great curves. That’s the beauty of the Darcy loveseat—made to suit your appreciation for clean, contemporary style. A striking flared frame, comfy pillow top armrests and an ultra-soft upholstery that holds up to everyday living complete this fashion statement.', CAST(1000.00 AS Decimal(18, 2)), 98, 2, CAST(900.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (7, N'Hodan Loveseat', N'Sometimes clean and simple is simply beautiful to behold. That’s the case with the Hodan loveseat. It’s contemporary—yet warm and inviting—in a soft, earthy shade with plush nap upholstery. Short on space? Thanks to its compact design and neutral appeal, it works great as main seating for smaller dwellings, too.', CAST(700.00 AS Decimal(18, 2)), 90, 2, CAST(666.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (8, N'Stringer Reclining Loveseat', N'If your home is your castle then comfort is king with the Stringer reclining loveseat. Stylish accent stitching, faux leather upholstery and sumptuously plush cushioning display the comfortable side of contemporary design.', CAST(688.00 AS Decimal(18, 2)), 85, 2, CAST(600.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (9, N'Pierin Loveseat', N'If you’re a fan of sleek comfort, Pierin loveseat is for you. Extra-thick seat cushions have a superb pocketed coil core. Back cushions and sloping shelter arms are equally comforting. Neutral upholstery is woven in a subtle, yet classic herringbone pattern. The two arm bolster pillows? Think of them as icing on the cake.', CAST(1200.00 AS Decimal(18, 2)), 91, 2, CAST(1100.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (10, N'Ballari Loveseat', N'Travel the West Indies from the comfort of home with the Ballari loveseat. The neutral-hued chenille upholstery lends a light and airy feel while stylish rolled arms add flair. Eye-catching throw pillows have a bold botanical print sure to liven up any space.', CAST(900.00 AS Decimal(18, 2)), 90, 2, CAST(866.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (11, N'Geordie Sofa Chaise', N'Relax any way you want with Geordie in the house. Stretch fully upon the three-seat sofa, or sit upright on the chaise, with feet fully extended and a remote control or magazine in hand. Flared arms and firm supportive cushions are covered in soft textured chenille. Included geometric pattern pillows stand out against the richly shaded seating. Reversible chaise can be set up either on right or left hand side.', CAST(495.00 AS Decimal(18, 2)), 94, 3, CAST(400.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (12, N'Patola Park 4-Piece Sectional', N'Patola Park 4-piece sectional packs plenty of contemporary style into a wonderfully cozy design, made for bringing folks together—beautifully. Feel free to be sitting pretty or laying back and lounging, depending upon your mood. We love how it marries crisp, clean lines with ultra-soft comfort. And with the angled cuddler, it really has the corner on cool layouts.', CAST(1388.00 AS Decimal(18, 2)), 92, 3, CAST(1000.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (13, N'Damacio 6-Piece Sectional With Power', N'Together time has never looked better. Damacio 6-piece sectional with power offers the luxury of top-grain leather in the seating area and skillfully incorporated leather match upholstery elsewhere. The result: comfort where it really counts, at a price that feels so nice. Beautifully stitched details have a cool flowing effect, while divided bustle back provides ample support. Dual cup holders and a pillow padded storage console are among the custom comforts. To top it off, you can recline in an infinite number of ways thanks to one-touch power control.', CAST(3488.00 AS Decimal(18, 2)), 93, 3, CAST(2000.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (14, N'Loric 3-Piece Sectional', N'Loric 3-piece sectional is beautiful proof that clean-lined contemporary style and exceptional comfort can go hand in hand. Cushions are plush yet supportive while luxe upholstery feels every bit as great as it looks. Three decorative pillows bring in just the right touch of zing.', CAST(2350.00 AS Decimal(18, 2)), 93, 3, CAST(1800.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (15, N'Jessa Place 3-Piece Sectional', N'Jessa Place 3-piece sectional has the corner on clean, contemporary style and neutral practicality. Plush yet supportive cushions offer both indulgence and structure, while pretty rolled arms round out the look with a touch of flair. Corner chaise is just the place when you’re really looking to kick back.', CAST(1388.00 AS Decimal(18, 2)), 92, 3, CAST(1000.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (16, N'Alliston DuraBlend® Sofa', N'Handsome lines, a supple feel and signature DuraBlend® craftsmanship—offering the look of weathered leather—make the Alliston sofa not only easy on the eyes but oh so comfortable. Factor in those ample padded arms and you’ve got the perfect spot for sacking out or curling up for a catnap.', CAST(1388.00 AS Decimal(18, 2)), 97, 4, CAST(1100.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (17, N'Corley Queen Sofa Sleeper', N'The suitlike upholstery of the Corley sofa sleeper is made less business and more pleasure with set-back arms and casual loose-back pillows. Coil seats provide optimal support. Feather throw pillows are dressed in large brocade, playful chevron and diagonal plaid patterns. A concealed queen mattress offers a good night’s sleep for overnight guests', CAST(1388.00 AS Decimal(18, 2)), 95, 4, CAST(1050.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (18, N'Zeth Twin Sofa Sleeper', N'With clean and simple lines, the Zeth sofa is at home with many different styles of decor. The comfortably firm seat cushions provide the optimal level of support and conceal a twin-size mattress. Thanks to an easy-lift mechanism, transforming the Zeth from sofa to bed is practically effortless.', CAST(2388.00 AS Decimal(18, 2)), 94, 4, CAST(2000.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (19, N'Ballari Queen Sofa Sleeper', N'Travel the West Indies from the comfort of home with the Ballari sofa. The neutral-hued chenille upholstery lends a light and airy feel while stylish rolled arms add flair. Throw pillows have an eye-catching botanical print and bold stripes sure to liven up any space. The sofa transforms into a queen-size bed, providing the perfect retreat for overnight guests.', CAST(1488.00 AS Decimal(18, 2)), 95, 4, CAST(1000.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (20, N'Dinelli Queen Sofa Sleeper', N'Tweed-like upholstery lends the Dinelli queen sofa sleeper a masculine feel, while playfully curved arms and whimsical feather throw pillows add the perfect touch of femininity. Thick piping highlights the piece’s clean, smooth lines. Soft but supportive cushions and padded arms make the Dinelli as comfortable as it is chic. Guests can stay the night in style thanks to the built-in queen mattress.', CAST(1999.00 AS Decimal(18, 2)), 99, 4, CAST(1500.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (21, N'Oberson Power Reclining Sofa', N'Though ruggedly handsome, the Oberson power reclining sofa is really a big softy. Offering the ultra-cool look of battered leather without the cold leather feel, this faux leather alternative affords warmth, character and durability. Whether you’re drawn to the studded nailhead trim, baseball stitching or jumbo window-panel support, trust that it’s easy on the eye and body.', CAST(3800.00 AS Decimal(18, 2)), 96, 5, CAST(3000.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (22, N'McNeil Power Recliner', N'Instant gratification. McNeil power recliner comes complete with that cool weatherworn quality you love, with no time investment required. A highly affordable alternative, the rich faux leather upholstery is tufted and stitched to perfection. Nailhead trim is the ultimate complement to chair''s handsome attire. One-touch power reclining puts infinite positions for comfort at your fingertips.', CAST(350.00 AS Decimal(18, 2)), 97, 5, CAST(300.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (23, N'Exhilaration Power Reclining Loveseat', N'Some people like having all the bells and whistles: Comfort. Power. Luxury. So you can see why they find the fashion-forward Exhilaration power reclining loveseat so exhilarating. It must be the plush feel and rich texture of all-leather seating. Or it could be that with one touch, they’ve got the power to recline in an infinite number of ways. Let’s not overlook the deeply tufted supportive padding with stitched detailing completing this slice of heaven.', CAST(700.00 AS Decimal(18, 2)), 97, 5, CAST(645.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (24, N'Darshmore Power Recliner', N'Kick back in the comfort of the Darshmore power rocker recliner. Pillowy cushions surround the entire body as you settle in for a movie or sleep marathon. Plush upholstery is welcoming and easy-care.', CAST(1500.00 AS Decimal(18, 2)), 97, 5, CAST(1300.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (25, N'Beadle Power Recliner', N'Style with substance. That’s the beauty of the Beadle power recliner. Cool baseball stitching hits a home run by crafting quite a sporty look. Best of all, the seating area and ultra plush pillow armrests are upholstered in top-grain leather for incredibly decadent comfort. Power element puts infinite reclining options at your fingertips.', CAST(800.00 AS Decimal(18, 2)), 98, 5, CAST(700.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (26, N'Porter Queen Panel Bed', N'The quality craftsmanship is clear to see. The classic design elements—including bun feet—are easy to love. Satisfying your taste for vintage inspiration, the Porter queen panel bed is elegant, without looking fussy. Mattress available, sold separately.', CAST(288.00 AS Decimal(18, 2)), 96, 6, CAST(200.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (27, N'Corraya Queen Panel Bed', N'The Corraya queen panel bed has all the markings of a modern masterpiece, from the clean edges to the arched headboard. Replicated cherry grain oozes extravagance, but only you''ll know you got the look for less. Adjustable height headboard accommodates pillow top mattresses or mountains of decorative pillows. Mattress available, sold separately.', CAST(1000.00 AS Decimal(18, 2)), 98, 6, CAST(800.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (28, N'Prentice Queen Panel Bed', N'Fresh, crisp and classic. Whether you’re going for shabby chic or cottage quaint, the Prentice queen panel bed is dressed to impress. Flat-panel design on headboard and footboard is clean and classic. Mattress available, sold separately.', CAST(700.00 AS Decimal(18, 2)), 97, 6, CAST(655.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (29, N'Wesling Queen Panel Bed', N'An urbane sense of style with just the right touch of laid-back attitude. Doubly cool two-tone design of the Wesling queen panel bed mixes dramatically distressed wood for beauty in the raw. Planking on the dimensional overlay panel is rife with saw marks and rich with character. Mattress available, sold separately.', CAST(588.00 AS Decimal(18, 2)), 99, 6, CAST(500.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (30, N'Sorinella Queen Upholstered Bed', N'To create this masterpiece, we transformed the glamour of yesteryear into something fresh and new. Sorinella queen bed sets a high note for affordable luxury, with diamond tufting highlighting the upholstered wing headboard. Platform footboard is beautifully upholstered, too.', CAST(588.00 AS Decimal(18, 2)), 100, 6, CAST(500.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (31, N'Tanshire Nightstand', N'Vintage inspiration with a softer side, Tanshire nightstand sets the stage for a restful retreat. Distressed finish with wire brushed detailing is simply a dream. Curved drawer profiles, triangular bail pulls in a bronze-tone finish and a scalloped apron bring shapely flow to the mix.', CAST(359.00 AS Decimal(18, 2)), 100, 7, CAST(300.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (32, N'Moriann Nightstand', N'What an eloquent sight to behold. Moriann night table has all the makings of a fine romance, including a dramatic curlicue metal base. Imagine it bedside or chairside.', CAST(145.00 AS Decimal(18, 2)), 100, 7, CAST(145.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (33, N'Kaslyn Nightstand', N'', CAST(284.00 AS Decimal(18, 2)), 100, 7, CAST(200.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (34, N'Dexifield Nightstand', N'Clean-lined and contemporary—but far from stark—the Dexifield nightstand is a fresh style awakening. Sleek, linear hardware in a sophisticated bronze tone plays perfectly with the chic yet earthy aesthetic.', CAST(179.00 AS Decimal(18, 2)), 100, 7, CAST(100.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (35, N'Prentice Nightstand', N'Whether you’re going for shabby chic or cottage quaint, the Prentice nightstand is dressed to impress. Three smooth-gliding drawers keep bedside essentials at hand. Knocker-style hardware in a satin-nickel tone is the perfect finishing touch.', CAST(319.00 AS Decimal(18, 2)), 100, 7, CAST(200.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (36, N'Cottage Retreat Dresser And Mirror', N'Make her room a sanctuary with the Cottage Retreat dresser and mirror. Creamy finish, bun feet and over-sized knobs appeal to vintage, shabby-chic and feminine aesthetics. Six large drawers help her keep a neat and orderly wardrobe. Subtle appliques accenting the mirror reflect exceptional taste.', CAST(145.00 AS Decimal(18, 2)), 100, 8, CAST(100.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (37, N'Larimer Dresser And Mirror', N'The Larimer dresser and mirror set takes cool elements of mid-century design and adds refined details that beautifully suit contemporary settings. Rich finish allows character of underlying veneer to shine through. Saber legs and center drawers with framed fronts add just enough “softness” to sleek lines. Chic concaved hardware completes the look.', CAST(245.00 AS Decimal(18, 2)), 100, 8, CAST(200.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (38, N'Kira Dresser', N'With its sculpted overlay drawer fronts, the Kira dresser carves out contemporary style in a fresh, new way. Its sophisticated, dark finish is given even more character with sculptural hardware in an aged bronze tone. Seven-drawer design packs great form and function.', CAST(400.00 AS Decimal(18, 2)), 100, 8, CAST(300.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (39, N'Harlinton Dresser And Mirror', N'With its unique two-tone treatment of replicated oak grain and metal, Harlinton dresser and mirror set is where modern meets vintage in the coolest possible way. Beautifully weathered treatment of the inlaid plank-style boards has a very “reclaimed” quality, while the metal framework and ultra clean-lined design is urban chic. Six smooth-gliding drawers make for an organized wardrobe.', CAST(355.00 AS Decimal(18, 2)), 99, 8, CAST(300.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (40, N'Zenfield Dresser And Mirror', N'Those drawn to the earthy character of a high-grain, textural finish—with rich tone variation—can appreciate the beauty and craftsmanship of the Zenfield dresser and mirror. The inspiration: industrial chic meets homey warmth. Unique elements such as metal banding, rivet accents and matching pulls in an aged pewter tone are part of Zenfield series'' distinctive charm. Use of durable hardwood and details including pair of felt-lined top drawers reflect Zenfield’s high quality from the inside out.', CAST(388.00 AS Decimal(18, 2)), 100, 8, CAST(300.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (41, N'Kaslyn Chest Of Drawers', N'Let your country cottage style shine through with the crisp and cheerful Kaslyn chest of drawers. The clean lines are enhanced with refined details such as classic half-round pilasters and beautifully turned bun feet. Five smooth-gliding drawers easily accommodate your wardrobe needs.', CAST(522.00 AS Decimal(18, 2)), 99, 9, CAST(400.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (42, N'Fanzere Chest Of Drawers', N'Fanzere chest of drawers has all the makings of a dreamy bedroom retreat. Heavily distressed acacia veneers and hardwoods boldly display saw marks resembling reclaimed lumber, while an antique glaze finish subtly showcases the richness of the grain. Bail pull and plate hardware incorporates a dab of industrial touch. Strikingly clean lines only add to the sublime design.', CAST(488.00 AS Decimal(18, 2)), 99, 9, CAST(400.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (43, N'Roddinton Chest Of Drawers', N'The beauty of the Roddinton chest of drawers lies in its classical moulded lines and impressive scale. Soft black undertones enrich the warm finish. Six roomy drawers are consistent with a master suite piece.', CAST(488.00 AS Decimal(18, 2)), 99, 9, CAST(400.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (44, N'Hadelyn Chest Of Drawers', N'Modern vintage heyday. Evoking so many qualities of heirloom furniture—painstaking details including a patina-style finish, double serpentine profile and classic bun feet—Hadelyn chest of drawers references the best of the past and reinterprets it for today''s more relaxed sensibilities. Refinements such fully finished drawers and striking knocker pulls in an aged bronze tone reflect Hadelyn''s attention to quality, inside and out.', CAST(300.00 AS Decimal(18, 2)), 100, 9, CAST(300.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (45, N'Dexifield Chest Of Drawers', N'Clean-lined and contemporary—but far from stark—the Dexifield dressing chest is a fresh style awakening. Sleek, linear hardware in a sophisticated bronze tone plays perfectly with the chic yet earthy aesthetic. Five ample drawers and three adjustable shelves stack up to an organized wardrobe.', CAST(549.00 AS Decimal(18, 2)), 100, 9, CAST(450.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (46, N'Larimer Dining Room TableZenfield Bedroom Bench', N'Those drawn to the earthy character of a high-grain, textural finish—with rich tone variation—can appreciate the beauty and craftsmanship of the Zenfield bench. Whether at the foot of the bed or the front of the foyer, it packs a stylish punch. The inspiration: industrial chic meets homey warmth. Aged pewter-tone metal banding and rivet accents—distinctive elements of the Zenfield series—evoke a touch of steamer trunk charm. Its versatility as a seating and storage piece that works in a multitude of spaces gives it all the more allure.', CAST(588.00 AS Decimal(18, 2)), 99, 10, CAST(500.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (47, N'North Shore Upholstered Bench', N'North Shore upholstered bench proves that Old World decor is irresistibly stylish. Classical plumes and medallions highlight the exposed frame.', CAST(499.00 AS Decimal(18, 2)), 100, 10, CAST(400.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (48, N'Moriann Bedroom Bench', N'The pedigree of the Moriann bench evokes the Regency era, yet the craftsmanship is thoroughly in line with today’s tastes. Graceful metal frame is softened with a rustic pewter-tone finish and twill upholstered seat. Imagine it at the foot of your bed or in the foyer.', CAST(225.00 AS Decimal(18, 2)), 99, 10, CAST(150.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (49, N'Key Town Upholstered Bench', N'Cheetah-pattern upholstery takes the colonial style for a walk on the wild side. Faux wicker drawer fronts bring just a touch of island style. Offering so much stash-away space, Key Town storage bench is fashionable and functional.', CAST(499.00 AS Decimal(18, 2)), 100, 10, CAST(400.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (50, N'Ledelle Upholstered Bench', N'Rosettes, beaded trim, roll arms and ornate appliques make the Ledelle bench look like a family heirloom. Faux leather seat has diamond-pattern topstitching and deep tufts. Shelf provides storage for shoes, throws, books and more.', CAST(900.00 AS Decimal(18, 2)), 100, 10, CAST(815.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (51, N'Lacey Dining Room Table', N'It''s time to add an aura of richness and elegance to your dining room arrangement. That''s where the Lacey dining room table comes in. Its chunky base design is paired with a thick faux marble top for the ultimate dining experience. Now your only worry is what to serve for dinner.', CAST(288.00 AS Decimal(18, 2)), 98, 11, CAST(200.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (52, N'Demarlos Dining Room Table', N'There’s nothing like vintage-inspired style to make you feel right at ease. Case in point: the Demarlos dining room extension table. Bathed in a rich finish with gently distressed accents, it serves up a beautifully relaxed sensibility.', CAST(288.00 AS Decimal(18, 2)), 100, 11, CAST(200.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (53, N'Glambrey Dining Room Table', N'Incorporating a touch of Old World glam with its scrolled metal accents, the Glambrey dining room table mixes various textural elements and rich earth tones in a way that’s so pleasing to the palette. We love how the circular inset glass on the dark finished tabletop serves up light and airy contrast. Whether you’re looking to entertain or simply make the best of your nest, do it with flair.', CAST(299.00 AS Decimal(18, 2)), 100, 11, CAST(200.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (54, N'Porter Dining Room Table', N'The quality craftsmanship is clear to see. The classic design elements—including richly turned legs—are easy to love. Satisfying your taste for traditional furnishings, the Porter dining room extension table is beautifully elegant, without looking fussy. Let style take shape with a square arrangement, or pop in the leaf to extend into a roomy rectangular table.', CAST(299.00 AS Decimal(18, 2)), 100, 11, CAST(200.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (55, N'Owingsville Dining Room Table', N'The farmhouse table is more in style than ever—and doesn’t the Owingsville rectangular dining room table bring it back in brilliant fashion. It dons a subtle two-tone finish for a look that''s equally homey and sophisticated. Vintage inspiration at its best. Seats six comfortably', CAST(224.00 AS Decimal(18, 2)), 100, 11, CAST(150.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (56, N'Larimer Dining Room Chair', N'Show your sense of exquisite taste by incorporating the Larimer dining room upholstered side chair into your space. Deep button-tufted upholstery in the most sophisticated shade is a welcome touch. Chair’s horizontal back slats are as stylish as they are supportive, lending a contrasting element of strength and structure to fabric''s softness.', CAST(100.00 AS Decimal(18, 2)), 100, 13, CAST(50.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (57, N'Trishelle Dining Room Chair', N'Trishelle pairs contemporary design with the comfort of an upholstered dining room side chair. Dark-finished legs and exposed back provide high drama. Opt for a vibrant pop of color or a subtle blend, depending on the upholstery shade you choose.', CAST(95.00 AS Decimal(18, 2)), 100, 13, CAST(40.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (58, N'Lacey Dining Room Chair', N'It''s time to add an aura of richness and comfort to your dining room seating. That''s where the Lacey dining room chair comes in. Its button-tufted faux leather upholstery is padded and curved for the ultimate seating experience. Now your only worry is what to serve for dinner.', CAST(58.00 AS Decimal(18, 2)), 100, 13, CAST(20.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (59, N'Demarlos Dining Room Chair', N'There’s nothing like vintage-inspired style to make you feel right at ease. Case in point: the Demarlos dining room side chair. Bathed in a rich finish with gently distressed accents, it serves up a beautifully relaxed sensibility. Textured upholstery incorporates a sense of softness and note of subtlety. Nailhead trim adds a punch of character.', CAST(128.00 AS Decimal(18, 2)), 100, 13, CAST(70.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (60, N'Chanella Dining Room Chair', N'Chanella upholstered dining room side chair offers a beautifully balanced serving of tailored style and plush comfort. The blend of “boxy” cushions softened by buttonless tufting is simply classic. Textured upholstery in a rich and practical neutral is so pleasing to the palette.', CAST(125.00 AS Decimal(18, 2)), 100, 13, CAST(70.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (61, N'Berlmine Counter Height Barstool', N'With its X-brace seat back and dramatic, dark finish, Berlmine bar stool does an “X-ceptionally” beautiful job of packing maximize style into a sleek design.', CAST(59.00 AS Decimal(18, 2)), 100, 14, CAST(10.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (62, N'Bantilly Counter Height Barstool', N'Back in the saddle. Bantilly backless saddle-seat counter height stool offers maximum style with minimal fuss. Industrial-inspired finish gives this classic choice modern appeal that works in so many settings', CAST(33.00 AS Decimal(18, 2)), 99, 14, CAST(10.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (63, N'Pinnadel Pub Height Barstool', N'Pinnadel pub height bar stool serves a sense of refinement without all the formality. A 180-degree spin element keeps you in the flow of conversation, while faux leather upholstered seating incorporates comfort and easy cleanup. The frame’s signature finish with a wire-brushed effect and gray undertones exudes an easy, relaxed sensibility.', CAST(125.00 AS Decimal(18, 2)), 99, 14, CAST(70.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (64, N'Owingsville Counter Height Barstool', N'The classic slat back chair is more in style than ever—and doesn’t the Owingsville bar stool bring it back in brilliant fashion. It dons a subtle two-tone finish for a look that''s equally homey and sophisticated. Vintage inspiration at its best.', CAST(75.00 AS Decimal(18, 2)), 99, 14, CAST(20.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (65, N'Moriann Counter Height Barstool', N'Taking a beautiful stance for modern vintage style, Moriann counter height stool sports a timeless look that works as well then as it does today. Among its head-turning features: intricately turned legs and a contoured saddle seat. A few well-placed “nicks” here and there give it an authentically timeworn appearance, as do hints of dark black undertones that enhance the aged patina effect', CAST(65.00 AS Decimal(18, 2)), 99, 14, CAST(15.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (66, N'Birnalla Dining Room Server', N'Crafted with a magnificent blend of wood and wood veneers, the Birnalla dining room server offers a beautifully rustic take on clean-lined design. We love how the subtle finish draws out the richness of the grain. And the bronze-tone hardware? An inspired touch. Four smooth-gliding drawers provide plenty of storage for linens, cutlery and such—bottom shelving serves special-occasion serving bowls, pottery and the like.', CAST(699.00 AS Decimal(18, 2)), 98, 15, CAST(600.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (67, N'Shallibay Dining Room Server', N'With its beautifully simple clean-lined design, Shallibay dining room server is deceptively simple. Yet with all that lies behind closed doors, it’s packed with possibilities. We’re talking dual-sided in-door storage to help you stock the bar, a 12-bottle wine rack, glass rack and drawer storage. And its straightforward lines and rich, rustic finish? A show of great taste.', CAST(599.00 AS Decimal(18, 2)), 99, 15, CAST(500.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (68, N'Krinden Dining Room Server', N'Cheers to the Krinden dining room server for nailing the stylish art of farmhouse-inspired design. Whether you’re a major connoisseur or a fan of $5 varieties—proudly put it on display in a built-in wine rack that holds six bottles. Adjustable shelves behind the doors and a smooth-gliding drawer are perfect for tucking away everything from linens and cloth napkins, to cutlery and serving utensils.', CAST(799.00 AS Decimal(18, 2)), 99, 15, CAST(700.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (69, N'Owingsville Dining Room Server', N'There are so many ingredients that make the Owingsville dining room server a tasteful choice. Let’s start with the two-tone detailing that''s an equal measure of homey and sophisticated. And the retro-inspired metal cup pulls? So charming. Top it off with display nooks and plenty of usable tabletop space—and you’ve got just the right recipe for chic vintage-inspired style.', CAST(499.00 AS Decimal(18, 2)), 99, 15, CAST(400.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (70, N'Tripton Dining Room Server', N'If you’re going for a subtly striking addition to your space, the Tripton dining room server creates a simply elegant place to showcase everything from your Sunday dinner best, to your everyday dishware. With its inset panels and distinctively aged-looking finish, this farmhouse-inspired piece is decidedly understated yet rich with character. Bridge-style turned metal legs give it just enough flair.', CAST(599.00 AS Decimal(18, 2)), 99, 15, CAST(500.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (71, N'Baraga 61" Home Office Desk', N'Baraga delivers the functionality you crave with the modern style that suits your taste. Frosted glass appears to float over the streamlined metal base. L-shape design sets up two different ways—a neat solution for how left-handed and right-handed users maximize the work space.', CAST(254.00 AS Decimal(18, 2)), 100, 12, CAST(200.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (72, N'Carlyle 48" Home Office Desk', N'If you''re big on contemporary style, look at how well the Carlyle home office desk could work in your space. Bathed in a rich finish—with tapered legs for a softening effect—it’s simple yet quite sophisticated. To maximize the desk space, a keyboard tray slides out of the drop-down drawer front when needed.', CAST(299.00 AS Decimal(18, 2)), 99, 12, CAST(200.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (73, N'Hamlyn 60" Home Office Desk', N'Simply exquisite, the Hamlyn home office desk blends stately, traditional style with a European flair. Reeded pilaster legs and ring pull hardware with an antique finish add Old World character—while a drop-down drawer front concealing pull-out keyboard tray is a modern convenience.', CAST(299.00 AS Decimal(18, 2)), 100, 12, CAST(200.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (74, N'Gavelston 48" Home Office Desk', N'Create a workspace eclectic in style. Whether you need a desk for your living room, office or your bedroom, the Gavelston desk gives you a unique place to work. With a convenient pull-out, drop-down key board, you can instantly type when you want without having to leave a keyboard out. Use the drawer to keep supplies within reach.', CAST(399.00 AS Decimal(18, 2)), 98, 12, CAST(300.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (75, N'Tanshire 61" Home Office Desk', N'Vintage inspiration with a softer side, Tanshire home office desk sets the stage for rustic refinement. Distressed finish with wire-brushed detailing is decidedly different. Classically turned legs and elegant curved stretcher add so much character, while clever touches such as a hidden keyboard tray subtly blend form with function.', CAST(599.00 AS Decimal(18, 2)), 99, 12, CAST(500.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
INSERT [dbo].[HAM_Product] ([ID], [ProductName], [ProductDescription], [ExpectedSalePrice], [Quantity], [CategoryID], [ImportPrice], [IsActive], [ImportDate]) VALUES (76, N'Birnalla 61" Home Office Desk', N'fferent. Classically turned legs and elegant curved stretcher add so much character, while clever touches such as a hidden keyboard tray subtly blend form with function.', CAST(500.00 AS Decimal(18, 2)), 100, 12, CAST(400.00 AS Decimal(18, 2)), 1, CAST(0x0000A54400000000 AS DateTime))
SET IDENTITY_INSERT [dbo].[HAM_Product] OFF
SET IDENTITY_INSERT [dbo].[HAM_Role] ON 

INSERT [dbo].[HAM_Role] ([ID], [Name], [IsActive]) VALUES (1, N'User', 1)
INSERT [dbo].[HAM_Role] ([ID], [Name], [IsActive]) VALUES (2, N'Admin', 1)
SET IDENTITY_INSERT [dbo].[HAM_Role] OFF
SET IDENTITY_INSERT [dbo].[HAM_Role_Action] ON 

INSERT [dbo].[HAM_Role_Action] ([ID], [RoleID], [ActionID]) VALUES (14, 1, 5)
INSERT [dbo].[HAM_Role_Action] ([ID], [RoleID], [ActionID]) VALUES (15, 1, 3)
INSERT [dbo].[HAM_Role_Action] ([ID], [RoleID], [ActionID]) VALUES (16, 1, 8)
INSERT [dbo].[HAM_Role_Action] ([ID], [RoleID], [ActionID]) VALUES (17, 1, 11)
INSERT [dbo].[HAM_Role_Action] ([ID], [RoleID], [ActionID]) VALUES (18, 1, 4)
INSERT [dbo].[HAM_Role_Action] ([ID], [RoleID], [ActionID]) VALUES (19, 1, 7)
INSERT [dbo].[HAM_Role_Action] ([ID], [RoleID], [ActionID]) VALUES (20, 1, 9)
INSERT [dbo].[HAM_Role_Action] ([ID], [RoleID], [ActionID]) VALUES (21, 1, 10)
INSERT [dbo].[HAM_Role_Action] ([ID], [RoleID], [ActionID]) VALUES (22, 1, 6)
INSERT [dbo].[HAM_Role_Action] ([ID], [RoleID], [ActionID]) VALUES (23, 1, 12)
INSERT [dbo].[HAM_Role_Action] ([ID], [RoleID], [ActionID]) VALUES (90, 2, 8)
INSERT [dbo].[HAM_Role_Action] ([ID], [RoleID], [ActionID]) VALUES (91, 2, 10)
INSERT [dbo].[HAM_Role_Action] ([ID], [RoleID], [ActionID]) VALUES (92, 2, 2)
INSERT [dbo].[HAM_Role_Action] ([ID], [RoleID], [ActionID]) VALUES (93, 2, 3)
INSERT [dbo].[HAM_Role_Action] ([ID], [RoleID], [ActionID]) VALUES (94, 2, 5)
INSERT [dbo].[HAM_Role_Action] ([ID], [RoleID], [ActionID]) VALUES (95, 2, 12)
INSERT [dbo].[HAM_Role_Action] ([ID], [RoleID], [ActionID]) VALUES (96, 2, 1)
INSERT [dbo].[HAM_Role_Action] ([ID], [RoleID], [ActionID]) VALUES (97, 2, 7)
INSERT [dbo].[HAM_Role_Action] ([ID], [RoleID], [ActionID]) VALUES (98, 2, 4)
INSERT [dbo].[HAM_Role_Action] ([ID], [RoleID], [ActionID]) VALUES (99, 2, 9)
INSERT [dbo].[HAM_Role_Action] ([ID], [RoleID], [ActionID]) VALUES (100, 2, 11)
INSERT [dbo].[HAM_Role_Action] ([ID], [RoleID], [ActionID]) VALUES (101, 2, 6)
SET IDENTITY_INSERT [dbo].[HAM_Role_Action] OFF
ALTER TABLE [dbo].[HAM_BillOrder]  WITH CHECK ADD  CONSTRAINT [FK_HAM_BillOrder_HAM_Customer] FOREIGN KEY([CustomerID])
REFERENCES [dbo].[HAM_Customer] ([ID])
GO
ALTER TABLE [dbo].[HAM_BillOrder] CHECK CONSTRAINT [FK_HAM_BillOrder_HAM_Customer]
GO
ALTER TABLE [dbo].[HAM_BillOrder]  WITH CHECK ADD  CONSTRAINT [FK_HAM_BillOrder_HAM_Employee] FOREIGN KEY([SellerID])
REFERENCES [dbo].[HAM_Employee] ([ID])
GO
ALTER TABLE [dbo].[HAM_BillOrder] CHECK CONSTRAINT [FK_HAM_BillOrder_HAM_Employee]
GO
ALTER TABLE [dbo].[HAM_Diary]  WITH CHECK ADD  CONSTRAINT [FK_HAM_Diary_HAM_Employee] FOREIGN KEY([AccountID])
REFERENCES [dbo].[HAM_Employee] ([ID])
GO
ALTER TABLE [dbo].[HAM_Diary] CHECK CONSTRAINT [FK_HAM_Diary_HAM_Employee]
GO
ALTER TABLE [dbo].[HAM_Employee]  WITH CHECK ADD  CONSTRAINT [FK_HAM_Employee_HAM_Role] FOREIGN KEY([RoleID])
REFERENCES [dbo].[HAM_Role] ([ID])
GO
ALTER TABLE [dbo].[HAM_Employee] CHECK CONSTRAINT [FK_HAM_Employee_HAM_Role]
GO
ALTER TABLE [dbo].[HAM_OrderLine]  WITH CHECK ADD  CONSTRAINT [FK_HAM_OrderLine_HAM_BillOrder] FOREIGN KEY([OrderID])
REFERENCES [dbo].[HAM_BillOrder] ([ID])
GO
ALTER TABLE [dbo].[HAM_OrderLine] CHECK CONSTRAINT [FK_HAM_OrderLine_HAM_BillOrder]
GO
ALTER TABLE [dbo].[HAM_OrderLine]  WITH CHECK ADD  CONSTRAINT [FK_HAM_OrderLine_HAM_Product] FOREIGN KEY([ProductID])
REFERENCES [dbo].[HAM_Product] ([ID])
GO
ALTER TABLE [dbo].[HAM_OrderLine] CHECK CONSTRAINT [FK_HAM_OrderLine_HAM_Product]
GO
ALTER TABLE [dbo].[HAM_Product]  WITH CHECK ADD  CONSTRAINT [FK_HAM_Product_HAM_Category] FOREIGN KEY([CategoryID])
REFERENCES [dbo].[HAM_Category] ([ID])
GO
ALTER TABLE [dbo].[HAM_Product] CHECK CONSTRAINT [FK_HAM_Product_HAM_Category]
GO
ALTER TABLE [dbo].[HAM_Role_Action]  WITH CHECK ADD  CONSTRAINT [FK_HAM_Role_Action_HAM_Action] FOREIGN KEY([ActionID])
REFERENCES [dbo].[HAM_Action] ([ID])
GO
ALTER TABLE [dbo].[HAM_Role_Action] CHECK CONSTRAINT [FK_HAM_Role_Action_HAM_Action]
GO
ALTER TABLE [dbo].[HAM_Role_Action]  WITH CHECK ADD  CONSTRAINT [FK_HAM_Role_Action_HAM_Role] FOREIGN KEY([RoleID])
REFERENCES [dbo].[HAM_Role] ([ID])
GO
ALTER TABLE [dbo].[HAM_Role_Action] CHECK CONSTRAINT [FK_HAM_Role_Action_HAM_Role]
GO
USE [master]
GO
ALTER DATABASE [FairDeal] SET  READ_WRITE 
GO
