USE [master]
GO
/****** Object:  Database [QLLaoDong]    Script Date: 5/13/2022 7:29:50 PM ******/
CREATE DATABASE [QLLaoDong] ON  PRIMARY 
( NAME = N'QLLaoDong_Data', FILENAME = N'D:\QLLaoDong_Data.mdf' , SIZE = 10240KB , MAXSIZE = 15360KB , FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'QLLaoDong_Log', FILENAME = N'D:\QLLaoDong_Log.ldf' , SIZE = 3072KB , MAXSIZE = 5120KB , FILEGROWTH = 1024KB )
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QLLaoDong].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QLLaoDong] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QLLaoDong] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QLLaoDong] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QLLaoDong] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QLLaoDong] SET ARITHABORT OFF 
GO
ALTER DATABASE [QLLaoDong] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [QLLaoDong] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QLLaoDong] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QLLaoDong] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QLLaoDong] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QLLaoDong] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QLLaoDong] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QLLaoDong] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QLLaoDong] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QLLaoDong] SET  ENABLE_BROKER 
GO
ALTER DATABASE [QLLaoDong] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QLLaoDong] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QLLaoDong] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QLLaoDong] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QLLaoDong] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QLLaoDong] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QLLaoDong] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QLLaoDong] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QLLaoDong] SET  MULTI_USER 
GO
ALTER DATABASE [QLLaoDong] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QLLaoDong] SET DB_CHAINING OFF 
GO
USE [QLLaoDong]
GO
/****** Object:  UserDefinedFunction [dbo].[Func_TinhLuongTheoThang]    Script Date: 5/13/2022 7:29:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[Func_TinhLuongTheoThang](@MaNV CHAR(6), @thang INT, @nam INT)
RETURNS MONEY
AS BEGIN
	DECLARE @luongTheoNgay MONEY, @luong MONEY, @soNgayLam INT
	SELECT @luongTheoNgay = LuongTheoNgay FROM NHANVIEN WHERE MaNV = @MaNV
	SELECT @soNgayLam = NgayLamViec FROM CHAMCONG WHERE @MaNV = MaNV AND @thang = Thang AND @nam = Nam
	SELECT @luong = @luongTheoNgay * @soNgayLam
	IF @luong IS NULL
		RETURN 0
	RETURN @luong
END
GO
/****** Object:  Table [dbo].[CHAMCONG]    Script Date: 5/13/2022 7:29:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CHAMCONG](
	[MaNV] [char](6) NOT NULL,
	[Thang] [nchar](2) NOT NULL,
	[Nam] [char](4) NOT NULL,
	[NgayLamViec] [int] NOT NULL,
	[NgaySuaDoi] [datetime] NULL,
	[DaNhan] [bit] NULL,
 CONSTRAINT [PK_CHAMCONG] PRIMARY KEY CLUSTERED 
(
	[MaNV] ASC,
	[Thang] ASC,
	[Nam] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CHUYENMON]    Script Date: 5/13/2022 7:29:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CHUYENMON](
	[MaCM] [char](6) NOT NULL,
	[TenCM] [nvarchar](40) NOT NULL,
	[MoTa] [nvarchar](100) NULL,
	[NgaySuaDoi] [datetime] NULL,
 CONSTRAINT [PK__CHUYENMO__27258E0F2E89E6CD] PRIMARY KEY CLUSTERED 
(
	[MaCM] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CONGTRINH]    Script Date: 5/13/2022 7:29:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CONGTRINH](
	[MaCT] [char](5) NOT NULL,
	[TenCT] [nvarchar](200) NOT NULL,
	[DiaChi] [nvarchar](100) NOT NULL,
	[NgayCapPhep] [datetime] NOT NULL,
	[NgayKhoiCong] [datetime] NULL,
	[NgayHoanThanhDuKien] [datetime] NULL,
	[TrangThai] [nvarchar](40) NOT NULL,
	[GhiChu] [nvarchar](300) NULL,
	[NgaySuaDoi] [datetime] NULL,
 CONSTRAINT [PK__CONGTRIN__27258E7453FE3D97] PRIMARY KEY CLUSTERED 
(
	[MaCT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CONGTRINH_CONGVIEC]    Script Date: 5/13/2022 7:29:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CONGTRINH_CONGVIEC](
	[MaCT] [char](5) NOT NULL,
	[MaCV] [char](5) NOT NULL,
	[soLuongToiDa] [int] NULL,
 CONSTRAINT [PK_CONGTRINH_CONGVIEC] PRIMARY KEY CLUSTERED 
(
	[MaCT] ASC,
	[MaCV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CONGVIEC]    Script Date: 5/13/2022 7:29:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CONGVIEC](
	[MaCV] [char](5) NOT NULL,
	[TenCV] [nvarchar](40) NOT NULL,
	[MoTa] [nvarchar](150) NULL,
	[MaCM] [char](6) NOT NULL,
	[NgaySuaDoi] [datetime] NULL,
 CONSTRAINT [PK__CONGVIEC__27258E76C955A024] PRIMARY KEY CLUSTERED 
(
	[MaCV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NHANVIEN]    Script Date: 5/13/2022 7:29:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NHANVIEN](
	[MaNV] [char](6) NOT NULL,
	[MaPB] [char](4) NULL,
	[MaCM] [char](6) NOT NULL,
	[HoNV] [nvarchar](40) NOT NULL,
	[TenNV] [nvarchar](40) NOT NULL,
	[GioiTinh] [bit] NOT NULL,
	[NgaySinh] [datetime] NOT NULL,
	[DiaChi] [nvarchar](150) NOT NULL,
	[SDT] [char](10) NOT NULL,
	[LuongTheoNgay] [money] NOT NULL,
	[NgaySuaDoi] [datetime] NULL,
 CONSTRAINT [PK__NHANVIEN__2725D70AACA34046] PRIMARY KEY CLUSTERED 
(
	[MaNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PHANCONG]    Script Date: 5/13/2022 7:29:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PHANCONG](
	[MaNV] [char](6) NOT NULL,
	[MaCT] [char](5) NOT NULL,
	[MaCV] [char](5) NOT NULL,
	[NgayThamGia] [datetime] NOT NULL,
	[NgaySuaDoi] [datetime] NULL,
 CONSTRAINT [PK_PHANCONG] PRIMARY KEY CLUSTERED 
(
	[MaNV] ASC,
	[MaCT] ASC,
	[MaCV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PHONGBAN]    Script Date: 5/13/2022 7:29:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PHONGBAN](
	[MaPB] [char](4) NOT NULL,
	[TenPB] [nvarchar](40) NOT NULL,
	[NgaySuaDoi] [datetime] NULL,
	[MaTruongPhong] [char](6) NULL,
 CONSTRAINT [PK__PHONGBAN__2725E7E47DC5E399] PRIMARY KEY CLUSTERED 
(
	[MaPB] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TAIKHOAN]    Script Date: 5/13/2022 7:29:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TAIKHOAN](
	[TenTK] [char](6) NOT NULL,
	[MatKhau] [char](32) NOT NULL,
	[NgaySuaDoi] [datetime] NULL,
 CONSTRAINT [PK__TAIKHOAN__4CF9E776A1B42E5E] PRIMARY KEY CLUSTERED 
(
	[TenTK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  UserDefinedFunction [dbo].[Func_ThongKe]    Script Date: 5/13/2022 7:29:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[Func_ThongKe]()
RETURNS TABLE
AS RETURN(
	SELECT TongNV = (SELECT COUNT(*) FROM NHANVIEN), 
		   TongDuAn = (SELECT COUNT(*) FROM CONGTRINH),
		   NVChuaThamGiaDuAn = (SELECT COUNT(*) FROM NHANVIEN WHERE MaNV NOT IN (SELECT MaNV FROM PHANCONG)),
		   TongPhongBan = (SELECT COUNT(*) FROM PHONGBAN),
		   DuAnDangThiCong = (SELECT COUNT(*) FROM CONGTRINH WHERE TrangThai = 'Thi công'),
		   DuAnHoanThanh = (SELECT COUNT(*) FROM CONGTRINH WHERE TrangThai = 'Hoàn thành')
)
GO
/****** Object:  View [dbo].[View_NhanVien]    Script Date: 5/13/2022 7:29:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[View_NhanVien] AS
SELECT MaNV, MaPB, MaCM, HoNV, TenNV, GioiTinh, NgaySinh, Tuoi = YEAR(GETDATE()) - YEAR(NgaySinh), DiaChi, SDT, LuongTheoNgay
FROM NHANVIEN
GO
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0001', N'1 ', N'2012', 10, CAST(N'2022-05-13T15:37:45.320' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0001', N'1 ', N'2022', 20, CAST(N'2022-05-12T13:58:08.083' AS DateTime), 0)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0001', N'4 ', N'2022', 19, CAST(N'2022-05-12T16:57:19.900' AS DateTime), 0)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0002', N'3 ', N'2022', 27, CAST(N'2022-05-12T16:57:16.910' AS DateTime), 0)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0003', N'10', N'2021', 30, CAST(N'2022-05-12T16:57:36.067' AS DateTime), 0)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0004', N'1 ', N'2022', 20, CAST(N'2022-05-12T16:57:10.507' AS DateTime), 0)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0006', N'2 ', N'2021', 29, CAST(N'2022-05-12T16:57:25.033' AS DateTime), 0)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0021', N'7 ', N'2021', 28, CAST(N'2022-05-12T16:57:49.533' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0030', N'12', N'2021', 25, CAST(N'2022-05-12T16:57:52.470' AS DateTime), 0)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0031', N'3 ', N'2022', 21, CAST(N'2022-05-12T16:58:02.627' AS DateTime), 0)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0035', N'1 ', N'2022', 22, CAST(N'2022-05-12T16:57:55.230' AS DateTime), 0)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0040', N'1 ', N'2022', 30, CAST(N'2022-05-12T16:57:57.870' AS DateTime), 0)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0089', N'2 ', N'2022', 29, CAST(N'2022-05-12T16:58:18.470' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0095', N'1 ', N'2022', 30, CAST(N'2022-05-12T16:58:22.507' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0097', N'4 ', N'2022', 26, CAST(N'2022-05-12T16:58:26.620' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0099', N'3 ', N'2022', 27, CAST(N'2022-05-12T16:59:02.120' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0100', N'4 ', N'2022', 28, CAST(N'2022-05-12T16:58:31.870' AS DateTime), 0)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0183', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0184', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0185', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0186', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0187', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0188', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0189', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0190', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0191', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0192', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0193', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0194', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0195', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0196', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0197', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0198', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0199', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0200', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0231', N'3 ', N'2022', 20, CAST(N'2022-05-12T16:59:14.777' AS DateTime), 0)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0233', N'5 ', N'2022', 19, CAST(N'2022-05-12T16:59:24.603' AS DateTime), 0)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0235', N'1 ', N'2022', 20, CAST(N'2022-05-12T17:00:01.830' AS DateTime), 0)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0239', N'11', N'2021', 29, CAST(N'2022-05-12T17:00:22.743' AS DateTime), 0)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0240', N'2 ', N'2022', 15, CAST(N'2022-05-12T17:00:09.023' AS DateTime), 0)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0241', N'12', N'2021', 30, CAST(N'2022-05-12T17:00:47.827' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0245', N'3 ', N'2022', 22, CAST(N'2022-05-12T17:01:50.470' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0249', N'3 ', N'2022', 22, CAST(N'2022-05-12T17:01:34.500' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0250', N'1 ', N'2022', 30, CAST(N'2022-05-12T17:00:59.517' AS DateTime), 0)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0259', N'2 ', N'2022', 25, CAST(N'2022-05-12T17:01:11.040' AS DateTime), 0)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0261', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0262', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0263', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0264', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0265', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0266', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0267', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0268', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0269', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0270', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0271', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0272', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0273', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0274', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0275', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0276', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0277', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0278', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0279', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0280', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0281', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0282', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0283', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0284', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0285', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0286', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0287', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0288', N'4 ', N'2022', 26, CAST(N'2022-05-12T21:23:09.643' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0290', N'1 ', N'2022', 25, CAST(N'2022-05-12T17:04:38.857' AS DateTime), 1)
INSERT [dbo].[CHAMCONG] ([MaNV], [Thang], [Nam], [NgayLamViec], [NgaySuaDoi], [DaNhan]) VALUES (N'NV0299', N'1 ', N'2021', 22, CAST(N'2022-05-12T17:04:02.373' AS DateTime), 0)
GO
INSERT [dbo].[CHUYENMON] ([MaCM], [TenCM], [MoTa], [NgaySuaDoi]) VALUES (N'CM0001', N'Kỹ sư thiết kế', N'Thiết kế bản vẽ', CAST(N'2022-05-05T17:05:11.983' AS DateTime))
INSERT [dbo].[CHUYENMON] ([MaCM], [TenCM], [MoTa], [NgaySuaDoi]) VALUES (N'CM0002', N'Thợ xây', N'', CAST(N'2022-05-06T16:53:42.717' AS DateTime))
INSERT [dbo].[CHUYENMON] ([MaCM], [TenCM], [MoTa], [NgaySuaDoi]) VALUES (N'CM0003', N'Thợ trát vữa', N'Trát tường', CAST(N'2022-05-06T17:00:14.377' AS DateTime))
INSERT [dbo].[CHUYENMON] ([MaCM], [TenCM], [MoTa], [NgaySuaDoi]) VALUES (N'CM0004', N'Thợ sơn', N'Sơn tường', CAST(N'2022-05-05T17:08:07.257' AS DateTime))
INSERT [dbo].[CHUYENMON] ([MaCM], [TenCM], [MoTa], [NgaySuaDoi]) VALUES (N'CM0005', N'Quản lý công trường', N'Quản lý công nhân công trường', CAST(N'2022-05-05T17:22:06.370' AS DateTime))
INSERT [dbo].[CHUYENMON] ([MaCM], [TenCM], [MoTa], [NgaySuaDoi]) VALUES (N'CM0006', N'Bảo vệ', N'Bảo vệ tại công trường xây dựng', CAST(N'2022-05-06T16:54:08.953' AS DateTime))
INSERT [dbo].[CHUYENMON] ([MaCM], [TenCM], [MoTa], [NgaySuaDoi]) VALUES (N'CM0007', N'Kiểm định', N'Kiểm tra chất lượng công trình', CAST(N'2022-05-05T17:24:21.970' AS DateTime))
INSERT [dbo].[CHUYENMON] ([MaCM], [TenCM], [MoTa], [NgaySuaDoi]) VALUES (N'CM0008', N'Đội trường công trường', N'Quản lý công nhân trong đội', CAST(N'2022-05-05T17:29:57.277' AS DateTime))
INSERT [dbo].[CHUYENMON] ([MaCM], [TenCM], [MoTa], [NgaySuaDoi]) VALUES (N'CM0009', N'Giám sát', N'Giám sát công nhân', CAST(N'2022-05-05T17:30:59.153' AS DateTime))
INSERT [dbo].[CHUYENMON] ([MaCM], [TenCM], [MoTa], [NgaySuaDoi]) VALUES (N'CM0010', N'Tư vấn giám sát', N'Tư vấn cho giám sát', CAST(N'2022-05-05T17:31:17.090' AS DateTime))
INSERT [dbo].[CHUYENMON] ([MaCM], [TenCM], [MoTa], [NgaySuaDoi]) VALUES (N'CM0011', N'Thợ hàn', N'Hàn sắt', CAST(N'2022-05-05T17:31:47.500' AS DateTime))
INSERT [dbo].[CHUYENMON] ([MaCM], [TenCM], [MoTa], [NgaySuaDoi]) VALUES (N'CM0012', N'Thợ điện', N'Đi điện cho công trình', CAST(N'2022-05-05T17:32:21.003' AS DateTime))
INSERT [dbo].[CHUYENMON] ([MaCM], [TenCM], [MoTa], [NgaySuaDoi]) VALUES (N'CM0013', N'Công nhân', NULL, CAST(N'2022-05-05T17:35:03.170' AS DateTime))
INSERT [dbo].[CHUYENMON] ([MaCM], [TenCM], [MoTa], [NgaySuaDoi]) VALUES (N'CM0014', N'Thợ đào móng', N'Đào móng công trình', CAST(N'2022-05-06T15:33:05.910' AS DateTime))
INSERT [dbo].[CHUYENMON] ([MaCM], [TenCM], [MoTa], [NgaySuaDoi]) VALUES (N'CM0015', N'Thợ nước', N'Xây dựng hệ thống ống nước', CAST(N'2022-05-06T15:35:34.290' AS DateTime))
INSERT [dbo].[CHUYENMON] ([MaCM], [TenCM], [MoTa], [NgaySuaDoi]) VALUES (N'CM0016', N'Thợ giàn giáo', N'Dựng giàn giáo cho công trình', CAST(N'2022-05-06T16:08:15.043' AS DateTime))
INSERT [dbo].[CHUYENMON] ([MaCM], [TenCM], [MoTa], [NgaySuaDoi]) VALUES (N'CM0017', N'Tư vấn pháp lý', NULL, CAST(N'2022-05-09T14:16:50.023' AS DateTime))
INSERT [dbo].[CHUYENMON] ([MaCM], [TenCM], [MoTa], [NgaySuaDoi]) VALUES (N'CM0018', N'Quản trị kinh doanh', NULL, CAST(N'2022-05-09T14:18:17.990' AS DateTime))
INSERT [dbo].[CHUYENMON] ([MaCM], [TenCM], [MoTa], [NgaySuaDoi]) VALUES (N'CM0019', N'Quản lý nhân sự', NULL, CAST(N'2022-05-09T14:21:23.747' AS DateTime))
GO
INSERT [dbo].[CONGTRINH] ([MaCT], [TenCT], [DiaChi], [NgayCapPhep], [NgayKhoiCong], [NgayHoanThanhDuKien], [TrangThai], [GhiChu], [NgaySuaDoi]) VALUES (N'CT001', N'Cải tạo công trình Khách sạn Hàm Rồng (3 sao)', N'SaPa, Lào Cai', CAST(N'2021-04-04T00:00:00.000' AS DateTime), CAST(N'2021-05-04T00:00:00.000' AS DateTime), CAST(N'2022-10-10T00:00:00.000' AS DateTime), N'Thi công', N'Thiếu nhân lực', CAST(N'2022-05-04T15:47:02.660' AS DateTime))
INSERT [dbo].[CONGTRINH] ([MaCT], [TenCT], [DiaChi], [NgayCapPhep], [NgayKhoiCong], [NgayHoanThanhDuKien], [TrangThai], [GhiChu], [NgaySuaDoi]) VALUES (N'CT002', N'Cải tạo nâng cấp Bệnh viện đa khoa số 1', N'Lào Cai', CAST(N'2016-07-03T00:00:00.000' AS DateTime), CAST(N'2017-06-10T00:00:00.000' AS DateTime), CAST(N'2023-08-02T00:00:00.000' AS DateTime), N'Thi công', NULL, CAST(N'2022-05-04T01:41:55.523' AS DateTime))
INSERT [dbo].[CONGTRINH] ([MaCT], [TenCT], [DiaChi], [NgayCapPhep], [NgayKhoiCong], [NgayHoanThanhDuKien], [TrangThai], [GhiChu], [NgaySuaDoi]) VALUES (N'CT003', N'Khách sạn Quốc tế Lào Cai', N'Lào Cai', CAST(N'2019-04-05T00:00:00.000' AS DateTime), CAST(N'2022-05-14T00:00:00.000' AS DateTime), CAST(N'2023-05-18T00:00:00.000' AS DateTime), N'Chờ khởi công', N'', CAST(N'2022-05-13T19:22:57.750' AS DateTime))
INSERT [dbo].[CONGTRINH] ([MaCT], [TenCT], [DiaChi], [NgayCapPhep], [NgayKhoiCong], [NgayHoanThanhDuKien], [TrangThai], [GhiChu], [NgaySuaDoi]) VALUES (N'CT004', N'Hội trường UBND huyện Mường Khương', N'Lào Cai', CAST(N'2017-01-10T00:00:00.000' AS DateTime), CAST(N'2017-07-08T00:00:00.000' AS DateTime), CAST(N'2021-01-25T00:00:00.000' AS DateTime), N'Hoàn thành', NULL, CAST(N'2022-05-04T02:12:47.453' AS DateTime))
INSERT [dbo].[CONGTRINH] ([MaCT], [TenCT], [DiaChi], [NgayCapPhep], [NgayKhoiCong], [NgayHoanThanhDuKien], [TrangThai], [GhiChu], [NgaySuaDoi]) VALUES (N'CT005', N'Trường đại học FPT', N'Long Thạnh Mỹ, Thủ Đức', CAST(N'2017-01-10T00:00:00.000' AS DateTime), CAST(N'2019-07-01T00:00:00.000' AS DateTime), NULL, N'Thi công', NULL, CAST(N'2022-05-09T14:25:36.107' AS DateTime))
INSERT [dbo].[CONGTRINH] ([MaCT], [TenCT], [DiaChi], [NgayCapPhep], [NgayKhoiCong], [NgayHoanThanhDuKien], [TrangThai], [GhiChu], [NgaySuaDoi]) VALUES (N'CT006', N'Cải tạo chợ Gò Vấp', N'Phường 4, Gò Vấp, HCM', CAST(N'2019-06-27T00:00:00.000' AS DateTime), CAST(N'2019-07-08T00:00:00.000' AS DateTime), CAST(N'2020-09-16T00:00:00.000' AS DateTime), N'Thi công', NULL, CAST(N'2022-05-04T15:35:10.143' AS DateTime))
INSERT [dbo].[CONGTRINH] ([MaCT], [TenCT], [DiaChi], [NgayCapPhep], [NgayKhoiCong], [NgayHoanThanhDuKien], [TrangThai], [GhiChu], [NgaySuaDoi]) VALUES (N'CT007', N'Nhà thi đấu đa năng Trung tâm VHTT', N'Phường 3, Gò Vấp, HCM', CAST(N'2021-07-22T00:00:00.000' AS DateTime), CAST(N'2023-03-13T00:00:00.000' AS DateTime), CAST(N'2025-12-11T00:00:00.000' AS DateTime), N'Chờ khởi công', N'', CAST(N'2022-05-13T18:33:33.497' AS DateTime))
INSERT [dbo].[CONGTRINH] ([MaCT], [TenCT], [DiaChi], [NgayCapPhep], [NgayKhoiCong], [NgayHoanThanhDuKien], [TrangThai], [GhiChu], [NgaySuaDoi]) VALUES (N'CT008', N'Cải tạo trụ sở ủy ban nhân dân quận Tân Bình', N'Trường Trinh, Phường 14, Tân Bình, HCM', CAST(N'2021-10-07T00:00:00.000' AS DateTime), CAST(N'2022-01-07T00:00:00.000' AS DateTime), CAST(N'2023-02-25T00:00:00.000' AS DateTime), N'Thi công', NULL, CAST(N'2022-05-04T15:40:53.887' AS DateTime))
INSERT [dbo].[CONGTRINH] ([MaCT], [TenCT], [DiaChi], [NgayCapPhep], [NgayKhoiCong], [NgayHoanThanhDuKien], [TrangThai], [GhiChu], [NgaySuaDoi]) VALUES (N'CT009', N'Nhà phố', N'Nguyễn Thái Sơn, Phường 4, Gò Vấp, HCM', CAST(N'2021-08-20T00:00:00.000' AS DateTime), CAST(N'2022-01-12T00:00:00.000' AS DateTime), CAST(N'2023-10-03T00:00:00.000' AS DateTime), N'Thi công', NULL, CAST(N'2022-05-04T15:43:30.967' AS DateTime))
INSERT [dbo].[CONGTRINH] ([MaCT], [TenCT], [DiaChi], [NgayCapPhep], [NgayKhoiCong], [NgayHoanThanhDuKien], [TrangThai], [GhiChu], [NgaySuaDoi]) VALUES (N'CT010', N'Nhà phố', N'Tân Phú, Quận 7, HCM', CAST(N'2021-10-17T00:00:00.000' AS DateTime), CAST(N'2022-06-03T00:00:00.000' AS DateTime), CAST(N'2024-06-13T00:00:00.000' AS DateTime), N'Chờ khởi công', NULL, CAST(N'2022-05-04T15:46:51.920' AS DateTime))
GO
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT001', N'CV001', 5)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT001', N'CV002', 8)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT001', N'CV005', 10)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT001', N'CV007', 12)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT001', N'CV009', 15)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT002', N'CV001', 10)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT002', N'CV003', 6)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT002', N'CV005', 2)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT002', N'CV006', 8)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT002', N'CV007', 14)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT003', N'CV002', 15)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT003', N'CV004', 13)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT003', N'CV006', 19)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT003', N'CV007', 11)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT003', N'CV008', 14)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT003', N'CV009', 10)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT003', N'CV010', 7)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT004', N'CV001', 5)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT004', N'CV002', 15)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT004', N'CV005', 14)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT004', N'CV008', 20)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT004', N'CV009', 21)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT005', N'CV001', 14)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT005', N'CV002', 7)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT005', N'CV003', 9)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT005', N'CV006', 5)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'Ct005', N'CV008', 6)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT005', N'CV009', 3)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT005', N'CV010', 14)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT006', N'CV001', 14)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT006', N'CV002', 17)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT006', N'CV004', 18)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT006', N'CV006', 13)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT006', N'CV007', 12)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT007', N'CV001', 16)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT007', N'CV002', 17)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT007', N'CV005', 15)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT007', N'CV007', 14)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT007', N'CV008', 12)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT007', N'CV010', 11)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT007', N'CV011', 14)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT008', N'CV001', 10)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT008', N'CV004', 7)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT008', N'CV005', 9)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT008', N'CV006', 6)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT008', N'CV007', 18)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT008', N'CV010', 18)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT008', N'CV011', 12)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT009', N'CV001', 15)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT009', N'CV002', 14)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT009', N'CV005', 11)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT009', N'CV007', 11)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT009', N'CV009', 10)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT009', N'CV011', 13)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT010', N'CV001', 15)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT010', N'CV002', 11)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT010', N'CV005', 10)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT010', N'CV007', 20)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT010', N'CV009', 2)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT010', N'CV010', 4)
INSERT [dbo].[CONGTRINH_CONGVIEC] ([MaCT], [MaCV], [soLuongToiDa]) VALUES (N'CT010', N'CV011', 10)
GO
INSERT [dbo].[CONGVIEC] ([MaCV], [TenCV], [MoTa], [MaCM], [NgaySuaDoi]) VALUES (N'CV001', N'Đào móng', N'Đào móng nền công trình', N'CM0014', CAST(N'2022-05-06T17:00:53.060' AS DateTime))
INSERT [dbo].[CONGVIEC] ([MaCV], [TenCV], [MoTa], [MaCM], [NgaySuaDoi]) VALUES (N'CV002', N'Xây tường', NULL, N'CM0002', CAST(N'2022-05-06T17:00:53.063' AS DateTime))
INSERT [dbo].[CONGVIEC] ([MaCV], [TenCV], [MoTa], [MaCM], [NgaySuaDoi]) VALUES (N'CV003', N'Đi đường ống nước', NULL, N'CM0015', CAST(N'2022-05-06T17:00:53.063' AS DateTime))
INSERT [dbo].[CONGVIEC] ([MaCV], [TenCV], [MoTa], [MaCM], [NgaySuaDoi]) VALUES (N'CV004', N'Thiết kế bản vẽ', N'Thiết kế bản vẽ cho công trình', N'CM0001', CAST(N'2022-05-06T17:00:53.063' AS DateTime))
INSERT [dbo].[CONGVIEC] ([MaCV], [TenCV], [MoTa], [MaCM], [NgaySuaDoi]) VALUES (N'CV005', N'Đội trưởng công trường', N'Đội trưởng quản lý công nhân công trình', N'CM0008', CAST(N'2022-05-06T17:01:30.067' AS DateTime))
INSERT [dbo].[CONGVIEC] ([MaCV], [TenCV], [MoTa], [MaCM], [NgaySuaDoi]) VALUES (N'CV006', N'Trát tường', NULL, N'CM0003', CAST(N'2022-05-06T17:01:21.293' AS DateTime))
INSERT [dbo].[CONGVIEC] ([MaCV], [TenCV], [MoTa], [MaCM], [NgaySuaDoi]) VALUES (N'CV007', N'Kiểm định', N'Kiểm tra chất lượng công trình', N'CM0007', CAST(N'2022-05-06T17:00:53.067' AS DateTime))
INSERT [dbo].[CONGVIEC] ([MaCV], [TenCV], [MoTa], [MaCM], [NgaySuaDoi]) VALUES (N'CV008', N'Dựng giàn giáo', NULL, N'CM0016', CAST(N'2022-05-06T17:00:53.067' AS DateTime))
INSERT [dbo].[CONGVIEC] ([MaCV], [TenCV], [MoTa], [MaCM], [NgaySuaDoi]) VALUES (N'CV009', N'Giám sát công trường', N'Giám sát công nhân', N'CM0009', CAST(N'2022-05-06T17:01:39.813' AS DateTime))
INSERT [dbo].[CONGVIEC] ([MaCV], [TenCV], [MoTa], [MaCM], [NgaySuaDoi]) VALUES (N'CV010', N'Đi đường điện công trường', NULL, N'CM0012', CAST(N'2022-05-06T17:02:49.857' AS DateTime))
INSERT [dbo].[CONGVIEC] ([MaCV], [TenCV], [MoTa], [MaCM], [NgaySuaDoi]) VALUES (N'CV011', N'Tư vấn giám sát', N'Tư vấn cho giám sát trường', N'CM0009', CAST(N'2022-05-06T17:04:03.883' AS DateTime))
GO
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0001', N'PB01', N'CM0019', N'Đặng Trọng', N'Quang', 1, CAST(N'1972-10-02T00:00:00.000' AS DateTime), N'Phường Phan Thiết, TP Tuyên Quang, Tỉnh Tuyên Quang', N'0355580620', 200000.0000, CAST(N'2022-05-11T22:27:58.813' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0002', N'PB01', N'CM0019', N'Lục Thiện ', N'Tâm', 1, CAST(N'1973-02-27T00:00:00.000' AS DateTime), N'152 Đinh Bộ Lĩnh, phường 9, TP. Mỹ Tho, Tiền Giang', N'0355545802', 200000.0000, CAST(N'2022-05-06T16:22:50.500' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0003', N'PB01', N'CM0019', N'Thân Thanh ', N'Hảo', 0, CAST(N'1974-02-22T00:00:00.000' AS DateTime), N'268 Trần Hưng Đạo, P. Nguyễn Cư Trinh, Q.1, TP. HCM', N'0355546640', 200000.0000, CAST(N'2022-05-06T16:22:53.293' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0004', N'PB01', N'CM0019', N'Lê Quỳnh ', N'Tiên', 0, CAST(N'1974-07-05T00:00:00.000' AS DateTime), N'8587 Trần Hưng Đạo, Hoàn Kiếm, TP. Hà Nội', N'0855586866', 200000.0000, CAST(N'2022-05-06T16:22:55.760' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0005', N'PB01', N'CM0019', N'Lý Huệ ', N'Hương', 0, CAST(N'1975-05-15T00:00:00.000' AS DateTime), N'02 Lê Đại Hành, P. Minh Khai, Q. Hồng Bàng, Tp. HP', N'0355548846', 200000.0000, CAST(N'2022-05-06T16:23:00.037' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0006', N'PB01', N'CM0019', N'Nguyễn Bích ', N'Chiêu', 0, CAST(N'1975-12-02T00:00:00.000' AS DateTime), N'80 Lê Lợi  Thành phố Đà Nẵng', N'0555553449', 200000.0000, CAST(N'2022-05-09T14:21:51.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0007', N'PB01', N'CM0019', N'La Phương ', N'Thủy', 0, CAST(N'1976-09-13T00:00:00.000' AS DateTime), N'9A Trần Phú, P. Cái Khế, Q. Ninh Kiều, TP. Cần Thơ', N'0755508665', 200000.0000, CAST(N'2022-05-09T14:21:51.667' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0008', N'PB01', N'CM0019', N'Huỳnh Linh ', N'Hà', 0, CAST(N'1977-11-26T00:00:00.000' AS DateTime), N'18 Lê Hồng Phong, P. Ba Đình, TP Thanh Hóa', N'0755515647', 200000.0000, CAST(N'2022-05-09T14:22:12.457' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0009', N'PB01', N'CM0019', N'Diệp Hoài ', N'Giang', 0, CAST(N'1978-06-30T00:00:00.000' AS DateTime), N'Đường Phan Thành Long, TP Long Xuyên, Tỉnh An Giang', N'0755543638', 200000.0000, CAST(N'2022-05-09T14:22:13.207' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0010', N'PB01', N'CM0019', N'Lý Quỳnh ', N'Nhung', 0, CAST(N'1978-08-12T00:00:00.000' AS DateTime), N'2 Thống Nhất, P.1, Tp. Vũng Tàu', N'0355580620', 200000.0000, CAST(N'2022-05-09T14:22:13.970' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0011', N'PB01', N'CM0006', N'Đặng Trọng ', N'Dũng', 1, CAST(N'1979-12-05T00:00:00.000' AS DateTime), N'3 Hoàng  Văn Thụ, P. Ngô Quyền, TP. Bắc Giang', N'0355545802', 200000.0000, CAST(N'2022-05-09T14:21:32.783' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0012', N'PB01', N'CM0006', N'Lục Thiện ', N'Tâm', 1, CAST(N'1980-01-06T00:00:00.000' AS DateTime), N'12 Trường Chinh, TP. Bắc Cạn, T. Bắc Cạn', N'0555507244', 200000.0000, CAST(N'2022-05-09T14:21:33.257' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0013', N'PB01', N'CM0006', N'Cao Ngọc ', N'Quang', 1, CAST(N'1981-05-23T00:00:00.000' AS DateTime), N'Đường Bà Triệu, Thị xã Bạc Liêu, Tỉnh Bạc Liêu', N'0755568790', 200000.0000, CAST(N'2022-05-09T14:21:33.810' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0014', N'PB01', N'CM0006', N'Trịnh Thái ', N'Duy', 1, CAST(N'1982-09-26T00:00:00.000' AS DateTime), N'14 Lý Thái Tổ, P. Suối Hoa, TP. Bắc Ninh', N'0955547720', 200000.0000, CAST(N'2022-05-09T14:21:34.277' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0015', N'PB01', N'CM0006', N'Thân Vĩnh ', N'Thọ', 1, CAST(N'1983-07-19T00:00:00.000' AS DateTime), N'4, Nguyễn Thái Học, P1, TP Trà Vinh, Tỉnh Trà Vinh', N'0355591606', 200000.0000, CAST(N'2022-05-09T14:21:34.780' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0016', N'PB01', N'CM0006', N'Võ Phước ', N'Sơn', 1, CAST(N'1983-09-06T00:00:00.000' AS DateTime), N'Phường Phan Thiết, TP Tuyên Quang, Tỉnh Tuyên Quang', N'0855500591', 200000.0000, CAST(N'2022-05-09T14:21:35.373' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0017', N'PB01', N'CM0006', N'Mai Mạnh ', N'Tấn', 1, CAST(N'1984-06-02T00:00:00.000' AS DateTime), N'152 Đinh Bộ Lĩnh, phường 9, TP. Mỹ Tho, Tiền Giang', N'0855557273', 200000.0000, CAST(N'2022-05-09T14:21:35.927' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0018', N'PB01', N'CM0006', N'Vũ Gia ', N'Cảnh', 1, CAST(N'1985-05-01T00:00:00.000' AS DateTime), N'02 Lê Đại Hành, P. Minh Khai, Q. Hồng Bàng, Tp. HP', N'0555562137', 200000.0000, CAST(N'2022-05-09T14:21:36.480' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0019', N'PB01', N'CM0006', N'Nguyễn Ngọc ', N'Quang', 1, CAST(N'1989-07-11T00:00:00.000' AS DateTime), N'Đường Hùng vương, Thành phố Sóc Trăng, Sóc Trăng', N'0355589257', 200000.0000, CAST(N'2022-05-09T14:21:37.090' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0020', N'PB01', N'CM0006', N'Vương Hoàng ', N'Ngôn', 1, CAST(N'1989-12-23T00:00:00.000' AS DateTime), N'71/72A Phó Cơ Điều, P.3, TP. Vĩnh Long, T.Vĩnh Long', N'0955519894', 200000.0000, CAST(N'2022-05-09T14:21:37.870' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0021', NULL, N'CM0008', N'Ngư Hạ ', N'Uyên', 0, CAST(N'1992-10-25T00:00:00.000' AS DateTime), N'Phường Liên Bảo, Thành Phố Vĩnh Yên, Vĩnh phúc', N'0555575706', 200000.0000, CAST(N'2022-05-13T18:34:07.880' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0022', N'PB02', N'CM0008', N'Trương Xuân ', N'Thủy', 0, CAST(N'1994-07-26T00:00:00.000' AS DateTime), N'17 Đinh Tiên Hoàng, Thành phố Yên Bái, Tỉnh Yên Bái', N'0355596423', 200000.0000, CAST(N'2022-05-09T01:47:49.343' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0023', N'PB02', N'CM0008', N'Đỗ Hồng ', N'Khuê', 0, CAST(N'1995-08-05T00:00:00.000' AS DateTime), N'Nguyễn Huệ, Phường 2, TP. Tuy Hòa, Phú Yên', N'0355585724', 200000.0000, CAST(N'2022-05-09T01:47:49.807' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0024', N'PB02', N'CM0008', N'Châu Lan ', N'Phương', 0, CAST(N'1996-08-17T00:00:00.000' AS DateTime), N'268 Trần Hưng Đạo, P. Nguyễn Cư Trinh, Q.1, TP. HCM', N'0355510680', 200000.0000, CAST(N'2022-05-09T01:47:50.337' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0025', N'PB02', N'CM0008', N'Ân Tố ', N'Uyên', 0, CAST(N'1997-05-07T00:00:00.000' AS DateTime), N'8587 Trần Hưng Đạo, Hoàn Kiếm, TP. Hà Nội', N'0755553567', 200000.0000, CAST(N'2022-05-09T01:47:50.883' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0026', N'PB02', N'CM0008', N'Đỗ Thục ', N'Trang', 0, CAST(N'1970-07-09T00:00:00.000' AS DateTime), N'02 Lê Đại Hành, P. Minh Khai, Q. Hồng Bàng, Tp. HP', N'0855548422', 200000.0000, CAST(N'2022-05-09T01:47:51.447' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0027', N'PB02', N'CM0008', N'Phan Minh ', N'Hiền', 0, CAST(N'1970-09-27T00:00:00.000' AS DateTime), N'80 Lê Lợi  Thành phố Đà Nẵng', N'0355530502', 200000.0000, CAST(N'2022-05-09T01:47:52.000' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0028', N'PB02', N'CM0008', N'Đào Hồng ', N'Thủy', 0, CAST(N'1974-06-27T00:00:00.000' AS DateTime), N'9A Trần Phú, P. Cái Khế, Q. Ninh Kiều, TP. Cần Thơ', N'0955537545', 200000.0000, CAST(N'2022-05-09T01:48:13.920' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0029', NULL, N'CM0008', N'Nguyễn Ngọc ', N'Diệp', 0, CAST(N'1974-09-11T00:00:00.000' AS DateTime), N'18 Lê Hồng Phong, P. Ba Đình, TP Thanh Hóa', N'0955582569', 200000.0000, CAST(N'2022-05-13T13:09:08.173' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0030', N'PB02', N'CM0008', N'Hồ Hồng ', N'Anh', 0, CAST(N'1975-05-25T00:00:00.000' AS DateTime), N'3 Hoàng  Văn Thụ, P. Ngô Quyền, TP. Bắc Giang', N'0755580798', 200000.0000, CAST(N'2022-05-09T01:48:15.313' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0031', N'PB02', N'CM0008', N'Dương Thiện ', N'Ðức', 1, CAST(N'1976-03-22T00:00:00.000' AS DateTime), N'12 Trường Chinh, TP. Bắc Cạn, T. Bắc Cạn', N'0955528114', 200000.0000, CAST(N'2022-05-09T01:43:11.347' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0032', N'PB02', N'CM0008', N'Nguyễn Lập ', N'Thành', 1, CAST(N'1977-01-14T00:00:00.000' AS DateTime), N'Đường Bà Triệu, Thị xã Bạc Liêu, Tỉnh Bạc Liêu', N'0355505943', 200000.0000, CAST(N'2022-05-09T01:43:13.960' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0033', N'PB02', N'CM0008', N'Chu Ðình ', N'Nam', 1, CAST(N'1977-04-14T00:00:00.000' AS DateTime), N'14 Lý Thái Tổ, P. Suối Hoa, TP. Bắc Ninh', N'0355597392', 200000.0000, CAST(N'2022-05-09T01:43:14.420' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0034', N'PB02', N'CM0008', N'Lưu Ðình ', N'Luận', 1, CAST(N'1977-08-24T00:00:00.000' AS DateTime), N'4, Nguyễn Thái Học, P1, TP Trà Vinh, Tỉnh Trà Vinh', N'0555599683', 200000.0000, CAST(N'2022-05-09T01:43:14.860' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0035', N'PB02', N'CM0008', N'Nguyễn Quốc ', N'Mạnh', 1, CAST(N'1977-09-26T00:00:00.000' AS DateTime), N'Phường Phan Thiết, TP Tuyên Quang, Tỉnh Tuyên Quang', N'0555548494', 200000.0000, CAST(N'2022-05-09T01:43:15.813' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0036', N'PB02', N'CM0008', N'Võ Ðức ', N'Khang', 1, CAST(N'1981-12-20T00:00:00.000' AS DateTime), N'152 Đinh Bộ Lĩnh, phường 9, TP. Mỹ Tho, Tiền Giang', N'0355526453', 200000.0000, CAST(N'2022-05-09T01:43:28.690' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0037', N'PB02', N'CM0009', N'Nguyễn Thế ', N'Bình', 1, CAST(N'1982-08-17T00:00:00.000' AS DateTime), N'71/72A Phó Cơ Điều, P.3, TP. Vĩnh Long, T.Vĩnh Long', N'0555575873', 200000.0000, CAST(N'2022-05-09T01:43:31.587' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0038', N'PB02', N'CM0009', N'Lê Vương ', N'Gia', 1, CAST(N'1986-08-19T00:00:00.000' AS DateTime), N'Phường Liên Bảo, Thành Phố Vĩnh Yên, Vĩnh phúc', N'0955505845', 200000.0000, CAST(N'2022-05-09T01:43:32.303' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0039', N'PB02', N'CM0009', N'Nghiêm Tuấn ', N'Chương', 1, CAST(N'1988-11-22T00:00:00.000' AS DateTime), N'17 Đinh Tiên Hoàng, Thành phố Yên Bái, Tỉnh Yên Bái', N'0355508889', 200000.0000, CAST(N'2022-05-09T01:43:32.890' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0040', N'PB02', N'CM0009', N'Ngư Duy ', N'Cẩn', 1, CAST(N'1988-12-02T00:00:00.000' AS DateTime), N'Nguyễn Huệ, Phường 2, TP. Tuy Hòa, Phú Yên', N'0555530495', 200000.0000, CAST(N'2022-05-09T01:43:33.573' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0041', N'PB02', N'CM0009', N'Hà Bảo ', N'Chấn', 1, CAST(N'1989-09-13T00:00:00.000' AS DateTime), N'13 Nam Hùng Vương, khu phố 3, phường An Lạc, Quận Bình Tân', N'0755575363', 200000.0000, CAST(N'2022-05-09T14:20:04.917' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0042', N'PB02', N'CM0009', N'Trần Phước ', N'Lộc', 1, CAST(N'1991-08-24T00:00:00.000' AS DateTime), N'142 Lê Công Phép, An Lạc, Quận Bình Tân', N'0855554941', 200000.0000, CAST(N'2022-05-09T14:20:06.853' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0043', N'PB02', N'CM0009', N'Triệu Tuấn ', N'Anh', 1, CAST(N'1992-09-11T00:00:00.000' AS DateTime), N'1948-1950 Võ Văn Kiệt, An Lạc, Quận Bình Tân', N'0555598473', 200000.0000, CAST(N'2022-05-09T14:20:07.410' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0044', N'PB02', N'CM0009', N'Hoàng Xuân ', N'Hòa', 1, CAST(N'1994-01-05T00:00:00.000' AS DateTime), N'1E 6 khu dân cư Nam Long, An Lạc, Quận Bình Tân', N'0555564128', 200000.0000, CAST(N'2022-05-09T14:20:07.897' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0045', N'PB02', N'CM0009', N'Liễu Tuấn ', N'Tú', 1, CAST(N'1994-09-02T00:00:00.000' AS DateTime), N'258/46 Hồ Học Lãm, Tổ 59, An Lạc, Quận Bình Tân', N'0355587449', 200000.0000, CAST(N'2022-05-09T14:20:08.480' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0046', N'PB02', N'CM0009', N'Trần Việt ', N'Quyết', 1, CAST(N'1994-11-30T00:00:00.000' AS DateTime), N'26/10 Lâm Hoành, An Lạc, Quận Bình Tân', N'0555581626', 200000.0000, CAST(N'2022-05-09T14:20:09.037' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0047', N'PB02', N'CM0009', N'Ngô Công ', N'Tráng', 1, CAST(N'1997-08-07T00:00:00.000' AS DateTime), N'26/19/11 Lâm Hoành, An Lạc, Quận Bình Tân', N'0555540979', 200000.0000, CAST(N'2022-05-09T14:20:09.670' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0048', N'PB02', N'CM0009', N'Đỗ Quang ', N'Hữu', 1, CAST(N'1998-05-10T00:00:00.000' AS DateTime), N'303 Hồ Học Lãm, phường An Lạc, Quận Bình Tân, An Lạc, Quận Bình Tân', N'0755521898', 200000.0000, CAST(N'2022-05-09T14:20:10.137' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0049', N'PB02', N'CM0009', N'Phan Ðình ', N'Sang', 1, CAST(N'1998-10-14T00:00:00.000' AS DateTime), N'309/16 Kinh Dương Vương, An Lạc, Quận Bình Tân', N'0355520477', 200000.0000, CAST(N'2022-05-09T14:20:10.753' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0050', N'PB02', N'CM0009', N'Đặng Lâm ', N'Trường', 1, CAST(N'1999-03-30T00:00:00.000' AS DateTime), N'439/27/43 Hồ Học Lãm, An Lạc, Quận Bình Tân', N'0355528764', 200000.0000, CAST(N'2022-05-09T14:20:11.793' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0051', N'PB02', N'CM0009', N'Phó Anh ', N'Tú', 1, CAST(N'1971-02-18T00:00:00.000' AS DateTime), N'500 Hồ Học Lãm, An Lạc, Quận Bình Tân', N'0555558999', 200000.0000, CAST(N'2022-05-09T14:17:22.153' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0052', N'PB02', N'CM0009', N'Trịnh Nhật ', N'Khương', 1, CAST(N'1971-03-24T00:00:00.000' AS DateTime), N'510 Hồ Học Lãm, An Lạc, Quận Bình Tân', N'0555585536', 200000.0000, CAST(N'2022-05-09T14:17:26.133' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0053', N'PB02', N'CM0009', N'Nguyễn Nam ', N'Việt', 1, CAST(N'1971-08-01T00:00:00.000' AS DateTime), N'574/16/6 Kinh Dương Vương, An Lạc, Quận Bình Tân', N'0355502923', 200000.0000, CAST(N'2022-05-09T14:17:26.810' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0054', N'PB02', N'CM0010', N'Dữu Bảo ', N'Tín', 1, CAST(N'1973-08-30T00:00:00.000' AS DateTime), N'664 Kinh Dương Vương, An Lạc, Quận Bình Tân', N'0355523446', 200000.0000, CAST(N'2022-05-09T14:17:27.420' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0055', N'PB02', N'CM0010', N'Trần Tiểu ', N'Bảo', 1, CAST(N'1973-11-16T00:00:00.000' AS DateTime), N'929 Quốc lộ 1, An Lạc, Quận Bình Tân', N'0755569711', 200000.0000, CAST(N'2022-05-09T14:17:28.077' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0056', NULL, N'CM0010', N'Châu Tường ', N'Lâm', 1, CAST(N'1973-12-25T00:00:00.000' AS DateTime), N'94 1 Khu phố 3, An Lạc, Quận Bình Tân', N'0355527450', 200000.0000, CAST(N'2022-05-13T14:14:51.477' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0057', N'PB02', N'CM0010', N'Phạm Trường ', N'Sơn', 1, CAST(N'1976-05-27T00:00:00.000' AS DateTime), N'Chợ khu phố 2, phường An Lạc, An Lạc, Quận Bình Tân', N'0555516102', 200000.0000, CAST(N'2022-05-09T14:17:29.300' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0058', NULL, N'CM0010', N'Thái Quang ', N'Dũng', 1, CAST(N'1977-08-24T00:00:00.000' AS DateTime), N'Chung cư 35 Hồ Học Lãm, An Lạc, Quận Bình Tân', N'0555550169', 200000.0000, CAST(N'2022-05-13T14:08:29.773' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0059', NULL, N'CM0010', N'Phan Ngọc ', N'Thuận', 1, CAST(N'1978-05-13T00:00:00.000' AS DateTime), N'Chung cư Ehome3 103 Hồ Học Lãm, phường An Lạc, An Lạc, Quận Bình Tân', N'0555554581', 200000.0000, CAST(N'2022-05-13T15:20:31.177' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0060', NULL, N'CM0010', N'Dương Minh ', N'Thuận', 1, CAST(N'1983-08-01T00:00:00.000' AS DateTime), N'13 Khu dân cư Nam Long, An Lạc, Quận Bình Tân', N'0955554257', 200000.0000, CAST(N'2022-05-13T15:20:27.737' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0061', N'PB02', N'CM0010', N'Chử Huy ', N'Tường', 1, CAST(N'1983-12-23T00:00:00.000' AS DateTime), N'2A Khu phố 3, An Lạc, Quận Bình Tân', N'0955563144', 200000.0000, CAST(N'2022-05-09T01:41:54.597' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0062', N'PB02', N'CM0010', N'Tôn Tuấn ', N'Hoàng', 1, CAST(N'1984-03-12T00:00:00.000' AS DateTime), N' 230 Hồ Học Lãm, An Lạc, Quận Bình Tân', N'0555592129', 200000.0000, CAST(N'2022-05-09T01:41:58.213' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0063', N'PB02', N'CM0010', N'Hà Như ', N'Khang', 1, CAST(N'1984-05-12T00:00:00.000' AS DateTime), N' 349 An Dương Vương, An Lạc, Quận Bình Tân', N'0355542963', 200000.0000, CAST(N'2022-05-09T01:41:58.750' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0064', N'PB02', N'CM0010', N'Cao Chấn ', N'Hùng', 1, CAST(N'1984-10-27T00:00:00.000' AS DateTime), N' 38 Nguyễn Hới, An Lạc, Quận Bình Tân', N'0555575767', 200000.0000, CAST(N'2022-05-09T01:41:59.543' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0065', N'PB02', N'CM0010', N'Nguyễn Bình ', N'Ðạt', 1, CAST(N'1985-01-14T00:00:00.000' AS DateTime), N' 55 Phan Đình Thông, An Lạc, Quận Bình Tân', N'0555539222', 200000.0000, CAST(N'2022-05-09T01:42:01.293' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0066', N'PB02', N'CM0010', N'Quyền Quốc ', N'Phương', 1, CAST(N'1986-02-14T00:00:00.000' AS DateTime), N'Khu phố 2,3,4 phường An Lạc, An Lạc, Quận Bình Tân', N'0355506632', 200000.0000, CAST(N'2022-05-09T14:20:31.347' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0067', N'PB02', N'CM0010', N'Nguyễn Nhật ', N'Quang', 1, CAST(N'1986-02-17T00:00:00.000' AS DateTime), N'26 Tên Lửa, An Lạc A, Quận Bình Tân', N'0755530870', 200000.0000, CAST(N'2022-05-09T14:20:31.980' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0068', N'PB02', N'CM0010', N'Nguyễn Thế ', N'Lâm', 1, CAST(N'1986-02-21T00:00:00.000' AS DateTime), N'40 Tên Lửa, An Lạc A, Quận Bình Tân', N'0855571284', 200000.0000, CAST(N'2022-05-09T14:20:32.577' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0069', N'PB02', N'CM0010', N'Nguyễn Việt ', N'Thắng', 1, CAST(N'1986-03-18T00:00:00.000' AS DateTime), N'100A Tên Lửa, kp 1, An Lạc A, Quận Bình Tân', N'0555545844', 200000.0000, CAST(N'2022-05-09T14:20:33.347' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0070', N'PB02', N'CM0010', N'Phạm Chấn ', N'Hùng', 1, CAST(N'1986-11-23T00:00:00.000' AS DateTime), N'148 1, kp 3, An Lạc A, Quận Bình Tân', N'0355518887', 200000.0000, CAST(N'2022-05-09T14:20:34.207' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0071', N'PB03', N'CM0002', N'Trang Duy ', N'Khang', 1, CAST(N'1991-10-24T00:00:00.000' AS DateTime), N'18 Tên Lửa, kp 1, An Lạc A, Quận Bình Tân', N'0355547331', 200000.0000, CAST(N'2022-05-09T01:44:11.967' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0072', N'PB03', N'CM0002', N'Mai Vĩnh ', N'Toàn', 1, CAST(N'1994-10-04T00:00:00.000' AS DateTime), N'3 2, kp 2, An Lạc A, Quận Bình Tân', N'0555545950', 200000.0000, CAST(N'2022-05-09T01:44:14.153' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0073', N'PB03', N'CM0002', N'Uất Phước ', N'Thiện', 1, CAST(N'1995-10-17T00:00:00.000' AS DateTime), N'36A Kinh Dương Vương, kp 1, An Lạc A, Quận Bình Tân', N'0755585516', 200000.0000, CAST(N'2022-05-09T01:44:14.623' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0074', N'PB03', N'CM0002', N'Nguyễn Phúc ', N'Thịnh', 1, CAST(N'1996-09-05T00:00:00.000' AS DateTime), N'4 6D, kp 8, An Lạc A, Quận Bình Tân', N'0955540796', 200000.0000, CAST(N'2022-05-09T01:44:15.303' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0075', N'PB03', N'CM0002', N'Nguyễn Duy ', N'Thông', 1, CAST(N'1997-11-19T00:00:00.000' AS DateTime), N'460/2 Kinh Dương Vương, kp 1, An Lạc A, Quận Bình Tân', N'0355532285', 200000.0000, CAST(N'2022-05-09T01:44:15.907' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0076', N'PB03', N'CM0002', N'Chử Ngọc ', N'Dũng', 1, CAST(N'1972-06-25T00:00:00.000' AS DateTime), N'54B Tạ Mỹ Duật, kp 3, An Lạc A, Quận Bình Tân', N'0555582869', 200000.0000, CAST(N'2022-05-09T01:44:16.470' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0077', N'PB03', N'CM0002', N'Lê Việt ', N'Thông', 1, CAST(N'1972-11-09T00:00:00.000' AS DateTime), N'86/9 Tên lửa, , kp 1, An Lạc A, Quận Bình Tân', N'0355509840', 200000.0000, CAST(N'2022-05-09T01:44:17.227' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0078', N'PB03', N'CM0002', N'Hồ Hữu ', N'Chiến', 1, CAST(N'1973-03-28T00:00:00.000' AS DateTime), N'150/49/2 26/3, Bình Hưng Hòa, Quận Bình Tân', N'0755511915', 200000.0000, CAST(N'2022-05-09T01:44:23.807' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0079', N'PB03', N'CM0002', N'Hà Vạn ', N'Thông', 1, CAST(N'1976-11-23T00:00:00.000' AS DateTime), N'16/23/16/6/14 9, kp 13, Bình Hưng Hòa, Quận Bình Tân', N'0555576812', 200000.0000, CAST(N'2022-05-09T01:44:24.490' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0080', N'PB03', N'CM0002', N'Đặng Hoàng ', N'Giang', 1, CAST(N'1980-05-18T00:00:00.000' AS DateTime), N'16/23/4/1 9, kp 13, Bình Hưng Hòa, Quận Bình Tân', N'0955593167', 200000.0000, CAST(N'2022-05-09T01:44:25.247' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0081', N'PB03', N'CM0002', N'Nguyễn Phương ', N'Nam', 1, CAST(N'1980-08-31T00:00:00.000' AS DateTime), N'16/23/6/4 9, Bình Hưng Hòa, Quận Bình Tân', N'0855571172', 200000.0000, CAST(N'2022-05-09T01:44:36.743' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0082', N'PB03', N'CM0002', N'Diệp Hùng ', N'Dũng', 1, CAST(N'1983-07-24T00:00:00.000' AS DateTime), N'191 Phạm Đăng Giảng, Bình Hưng Hòa, Quận Bình Tân', N'0555512208', 200000.0000, CAST(N'2022-05-09T01:44:38.927' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0083', N'PB03', N'CM0002', N'Nghiêm Huy ', N'Hoàng', 1, CAST(N'1984-06-17T00:00:00.000' AS DateTime), N'219/51 5, Bình Hưng Hòa, Quận Bình Tân', N'0955508851', 200000.0000, CAST(N'2022-05-09T01:44:39.437' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0084', N'PB03', N'CM0002', N'Lý Hồng ', N'Đức', 1, CAST(N'1985-05-13T00:00:00.000' AS DateTime), N'246/48 26/3, Bình Hưng Hòa, Quận Bình Tân', N'0555566937', 200000.0000, CAST(N'2022-05-09T01:44:39.963' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0085', N'PB03', N'CM0002', N'Hồ Thành ', N'Sang', 1, CAST(N'1985-08-01T00:00:00.000' AS DateTime), N'246/49/2 26/3, Bình Hưng Hòa, Quận Bình Tân', N'0555557135', 200000.0000, CAST(N'2022-05-09T01:44:40.533' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0086', N'PB03', N'CM0002', N'Hồ Quốc ', N'Phong', 1, CAST(N'1988-09-22T00:00:00.000' AS DateTime), N'246/6 26/3, Bình Hưng Hòa, Quận Bình Tân', N'0555505937', 200000.0000, CAST(N'2022-05-09T01:44:41.083' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0087', N'PB03', N'CM0002', N'Trần Tùng ', N'Lâm', 1, CAST(N'1989-12-19T00:00:00.000' AS DateTime), N'257 Phạm Đăng Giảng, Bình Hưng Hòa, Quận Bình Tân', N'0755507815', 200000.0000, CAST(N'2022-05-09T01:44:41.623' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0088', N'PB03', N'CM0003', N'Lý Công ', N'Hào', 1, CAST(N'1992-01-24T00:00:00.000' AS DateTime), N'403/2/9 hương lộ 3, Bình Hưng Hòa, Quận Bình Tân', N'0355588766', 200000.0000, CAST(N'2022-05-09T01:44:42.173' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0089', N'PB03', N'CM0003', N'Dương Danh ', N'Văn', 1, CAST(N'1992-08-08T00:00:00.000' AS DateTime), N'435/1 Hương lộ 3, Bình Hưng Hòa, Quận Bình Tân', N'0955599931', 200000.0000, CAST(N'2022-05-09T01:44:42.687' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0090', N'PB03', N'CM0003', N'Giang Khắc ', N'Việt', 1, CAST(N'1993-06-09T00:00:00.000' AS DateTime), N'435/1 Hương Lộ 3, kp 19, Bình Hưng Hòa, Quận Bình Tân', N'0555504891', 200000.0000, CAST(N'2022-05-09T01:44:43.320' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0091', N'PB03', N'CM0003', N'Hà Gia ', N'Nghị', 1, CAST(N'1995-03-02T00:00:00.000' AS DateTime), N'481 quốc lộ 1, Bình Hưng Hòa, Quận Bình Tân', N'0355570755', 200000.0000, CAST(N'2022-05-09T01:44:56.540' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0092', N'PB03', N'CM0003', N'Phan Hữu ', N'Thống', 1, CAST(N'1996-11-09T00:00:00.000' AS DateTime), N'50/17/17A 5, Bình Hưng Hòa, Quận Bình Tân', N'0555586114', 200000.0000, CAST(N'2022-05-09T01:44:58.507' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0093', N'PB03', N'CM0003', N'Thân Danh ', N'Thành', 1, CAST(N'1997-01-27T00:00:00.000' AS DateTime), N'557/63 Hương lộ 3, Bình Hưng Hòa, Quận Bình Tân', N'0555565127', 200000.0000, CAST(N'2022-05-09T01:44:59.043' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0094', N'PB03', N'CM0003', N'Hồ Việt ', N'Hùng', 1, CAST(N'1997-08-27T00:00:00.000' AS DateTime), N'557/63 Hương lộ 3, kp 9, Bình Hưng Hòa, Quận Bình Tân', N'0355547669', 200000.0000, CAST(N'2022-05-09T01:44:59.600' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0095', N'PB03', N'CM0003', N'Nguyễn Trường ', N'Sơn', 1, CAST(N'1997-10-09T00:00:00.000' AS DateTime), N'638/47 Lê Trọng Tấn, Bình Hưng Hòa, Quận Bình Tân', N'0955580611', 200000.0000, CAST(N'2022-05-09T01:45:01.610' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0096', N'PB03', N'CM0003', N'Bùi Việt ', N'Khôi', 1, CAST(N'1998-06-04T00:00:00.000' AS DateTime), N'72 10, Bình Hưng Hòa, Quận Bình Tân', N'0755516192', 200000.0000, CAST(N'2022-05-09T01:45:02.137' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0097', N'PB03', N'CM0003', N'Bùi Anh ', N'Dũng', 1, CAST(N'2000-07-24T00:00:00.000' AS DateTime), N'754/3 Tân Kỳ - Tân Quý, Bình Hưng Hòa, Quận Bình Tân', N'0955589162', 200000.0000, CAST(N'2022-05-09T01:45:02.653' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0098', N'PB03', N'CM0003', N'Thi Gia ', N'Ðức', 1, CAST(N'2000-09-06T00:00:00.000' AS DateTime), N'79/71/3 4, Bình Hưng Hòa, Quận Bình Tân', N'0555507869', 200000.0000, CAST(N'2022-05-09T01:45:03.273' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0099', N'PB03', N'CM0003', N'Hồ Phúc ', N'Sinh', 1, CAST(N'2000-10-19T00:00:00.000' AS DateTime), N'80 18, Bình Hưng Hòa, Quận Bình Tân', N'0555501431', 200000.0000, CAST(N'2022-05-09T01:45:04.010' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0100', N'PB03', N'CM0003', N'Lý Thanh ', N'Toản', 1, CAST(N'2001-03-01T00:00:00.000' AS DateTime), N'80 18, kp 2, Bình Hưng Hòa, Quận Bình Tân', N'0555581263', 200000.0000, CAST(N'2022-05-09T01:45:05.003' AS DateTime))
GO
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0101', N'PB03', N'CM0003', N'Đoàn An ', N'Hạ', 0, CAST(N'1970-11-09T00:00:00.000' AS DateTime), N'99 Phạm Đăng Giảng, P. Bình Hưng Hòa, Bình Tân, Bình Hưng Hòa, Quận Bình Tân', N'0355519267', 200000.0000, CAST(N'2022-05-11T13:11:54.140' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0102', N'PB03', N'CM0003', N'Phạm Thanh ', N'Tâm', 0, CAST(N'1973-03-27T00:00:00.000' AS DateTime), N'9A 13, P. Bình Hưng Hòa, Q. Bình Tân, Bình Hưng Hòa, Quận Bình Tân', N'0355576260', 200000.0000, CAST(N'2022-05-10T00:36:53.320' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0103', N'PB03', N'CM0003', N'Đoàn Xuân ', N'Vân', 0, CAST(N'1973-06-27T00:00:00.000' AS DateTime), N'Chung Cư Gia Phú, Bình Hưng Hòa, Quận Bình Tân', N'0755577710', 200000.0000, CAST(N'2022-05-10T00:36:58.100' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0104', N'PB03', N'CM0003', N'Chử Thu ', N'Hồng', 0, CAST(N'1977-03-10T00:00:00.000' AS DateTime), N'Kế 69/52 3, Bình Hưng Hòa, Quận Bình Tân', N'0755545902', 200000.0000, CAST(N'2022-05-10T00:36:58.597' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0105', N'PB03', N'CM0004', N'Lê Thùy ', N'Mi', 0, CAST(N'1977-10-25T00:00:00.000' AS DateTime), N'Lê Văn Quới khu phố 24, Bình Hưng Hòa A, Quận Bình Tân', N'0755566960', 200000.0000, CAST(N'2022-05-10T00:36:59.123' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0106', N'PB03', N'CM0004', N'Lê Diệp ', N'Anh', 0, CAST(N'1980-02-10T00:00:00.000' AS DateTime), N'110/2/16 4, KP3, P Bình Hưng Hòa A, Q Bình Tân, Bình Hưng Hòa A, Quận Bình Tân', N'0355521547', 200000.0000, CAST(N'2022-05-10T00:36:59.673' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0107', N'PB03', N'CM0004', N'Nguyễn Phương ', N'Thủy', 0, CAST(N'1980-07-22T00:00:00.000' AS DateTime), N'38 10 khu phố 15, Bình Hưng Hòa A, Quận Bình Tân', N'0555512959', 200000.0000, CAST(N'2022-05-10T00:37:00.317' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0108', N'PB03', N'CM0004', N'Trầm Diệp ', N'Chi', 0, CAST(N'1982-08-30T00:00:00.000' AS DateTime), N'Gò Xoài khu phố 3, Bình Hưng Hòa A, Quận Bình Tân', N'0855565923', 200000.0000, CAST(N'2022-05-10T00:37:02.557' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0109', N'PB03', N'CM0004', N'Tạ Thiên ', N'Phương', 0, CAST(N'1982-12-22T00:00:00.000' AS DateTime), N'Lê Văn Quới khu phố 24, Bình Hưng Hòa A, Quận Bình Tân', N'0355500122', 200000.0000, CAST(N'2022-05-10T00:37:03.073' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0110', N'PB03', N'CM0004', N'Vũ Ánh ', N'Thơ', 0, CAST(N'1984-01-18T00:00:00.000' AS DateTime), N'Lê Văn Quới khu phố 26, Bình Hưng Hòa A, Quận Bình Tân', N'0555522519', 200000.0000, CAST(N'2022-05-10T00:37:03.547' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0111', N'PB03', N'CM0004', N'Hồ Bảo ', N'Anh', 0, CAST(N'1986-04-03T00:00:00.000' AS DateTime), N'Liên khu 8-9 khu phố 8, Bình Hưng Hòa A, Quận Bình Tân', N'0555581376', 200000.0000, CAST(N'2022-05-10T00:37:04.013' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0112', N'PB03', N'CM0004', N'Kim Mộng ', N'Vi', 0, CAST(N'1986-07-10T00:00:00.000' AS DateTime), N'Miếu Gò Xoài khu phố 11, Bình Hưng Hòa A, Quận Bình Tân', N'0855509133', 200000.0000, CAST(N'2022-05-10T00:37:04.510' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0113', N'PB03', N'CM0004', N'Trần Thanh ', N'Hiền', 0, CAST(N'1986-09-22T00:00:00.000' AS DateTime), N'12 khu phố 15, Bình Hưng Hòa A, Quận Bình Tân', N'0755566259', 200000.0000, CAST(N'2022-05-10T00:37:05.003' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0114', N'PB03', N'CM0004', N'Nguyễn Huệ ', N'My', 0, CAST(N'1987-03-15T00:00:00.000' AS DateTime), N'12 khu phố 25, Bình Hưng Hòa A, Quận Bình Tân', N'0855542127', 200000.0000, CAST(N'2022-05-10T00:37:05.513' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0115', N'PB03', N'CM0004', N'Nguyễn Xuân ', N'Thanh', 0, CAST(N'1987-06-12T00:00:00.000' AS DateTime), N'1A khu phố 26, Bình Hưng Hòa A, Quận Bình Tân', N'0555589760', 200000.0000, CAST(N'2022-05-10T00:37:06.027' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0116', N'PB03', N'CM0004', N'Trần Trúc ', N'Lan', 0, CAST(N'1988-06-06T00:00:00.000' AS DateTime), N'4 khu phố 9, Bình Hưng Hòa A, Quận Bình Tân', N'0355587394', 200000.0000, CAST(N'2022-05-10T00:37:06.537' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0117', N'PB03', N'CM0004', N'Mạch Trúc ', N'Phương', 0, CAST(N'1991-08-11T00:00:00.000' AS DateTime), N'5 Bình Hưng Hòa A, Quận Bình Tân', N'0755527505', 200000.0000, CAST(N'2022-05-10T00:37:19.410' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0118', N'PB03', N'CM0004', N'Nguyễn Lan ', N'Vy', 0, CAST(N'1994-02-19T00:00:00.000' AS DateTime), N'96 24, khu phố 11, Bình Hưng Hòa A, Quận Bình Tân', N'0555523656', 200000.0000, CAST(N'2022-05-10T00:37:20.220' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0119', N'PB03', N'CM0004', N'Nguyễn Ngọc ', N'Trinh', 0, CAST(N'1994-12-02T00:00:00.000' AS DateTime), N'268 Nguyễn Thị Tú, p. Bình Hưng Hòa B, Quận Bình Tân', N'0855589878', 200000.0000, CAST(N'2022-05-10T00:37:20.940' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0120', N'PB03', N'CM0004', N'Văn Thảo ', N'Hương', 0, CAST(N'1995-06-14T00:00:00.000' AS DateTime), N'37/23/8/4 Hồ Văn Long, P. Bình Hưng Hòa B, Quận Bình Tân', N'0355593576', 200000.0000, CAST(N'2022-05-10T00:37:21.883' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0121', N'PB03', N'CM0004', N'Lê Diễm ', N'Quyên', 0, CAST(N'1996-12-26T00:00:00.000' AS DateTime), N'118/18/69 Liên Khu 5-6, khu phố 7, Bình Hưng Hòa B, Quận Bình Tân', N'0355513252', 200000.0000, CAST(N'2022-05-10T00:37:22.690' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0122', N'PB03', N'CM0004', N'Ngô Diễm ', N'Trinh', 0, CAST(N'1998-05-05T00:00:00.000' AS DateTime), N'175/2/33 Hồ Văn Long, khu phố 1, Bình Hưng Hòa B, Quận Bình Tân', N'0555598830', 200000.0000, CAST(N'2022-05-10T00:37:23.573' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0123', N'PB03', N'CM0011', N'Đỗ Minh ', N'Thư', 0, CAST(N'2000-08-17T00:00:00.000' AS DateTime), N'21 10, khu phố 7, Bình Hưng Hòa B, Quận Bình Tân', N'0355519247', 200000.0000, CAST(N'2022-05-10T00:37:37.203' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0124', N'PB03', N'CM0011', N'Hồ Thanh ', N'Hương', 0, CAST(N'2000-08-21T00:00:00.000' AS DateTime), N'213/68 Liên Khu 4-5, Khu phố 5, Bình Hưng Hòa B, Quận Bình Tân', N'0355521580', 200000.0000, CAST(N'2022-05-10T00:37:39.597' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0125', N'PB03', N'CM0011', N'Trần Quỳnh ', N'Hoa', 0, CAST(N'2000-12-07T00:00:00.000' AS DateTime), N'214 Bình Thành, khu phố 4, Bình Hưng Hòa B, Quận Bình Tân', N'0955570694', 200000.0000, CAST(N'2022-05-10T00:37:40.250' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0126', N'PB03', N'CM0011', N'Dương Vân ', N'Khánh', 0, CAST(N'1970-09-01T00:00:00.000' AS DateTime), N'30 10A, khu phố 4, Bình Hưng Hòa B, Quận Bình Tân', N'0955533728', 200000.0000, CAST(N'2022-05-10T00:37:40.900' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0127', N'PB03', N'CM0011', N'Dương Bạch ', N'Yến', 0, CAST(N'1972-07-18T00:00:00.000' AS DateTime), N'304/60 Hương Lộ 80, khu phố 2, Bình Hưng Hòa B, Quận Bình Tân', N'0355586237', 200000.0000, CAST(N'2022-05-10T00:37:41.557' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0128', N'PB03', N'CM0011', N'Nguyễn Diễm ', N'Trang', 0, CAST(N'1972-09-17T00:00:00.000' AS DateTime), N'433 Bình Thành, khu phố 2, Bình Hưng Hòa B, Quận Bình Tân', N'0855580929', 200000.0000, CAST(N'2022-05-10T00:37:42.263' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0129', N'PB03', N'CM0011', N'An Bảo ', N'Quỳnh', 0, CAST(N'1973-06-26T00:00:00.000' AS DateTime), N' 308 Quốc Lộ 1, khu phố 3, Bình Hưng Hòa B, Quận Bình Tân', N'0355508365', 200000.0000, CAST(N'2022-05-10T00:37:43.030' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0130', N'PB03', N'CM0011', N'Lê Khả ', N'Tú', 0, CAST(N'1976-12-07T00:00:00.000' AS DateTime), N' 357/34 Bình Thành, khu phố 2, Bình Hưng Hòa B, Quận Bình Tân', N'0555540903', 200000.0000, CAST(N'2022-05-10T00:37:51.727' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0131', N'PB03', N'CM0011', N'Hồ Phượng ', N'Tiên', 0, CAST(N'1977-04-25T00:00:00.000' AS DateTime), N' 79 Cây Cám, khu phố 11, Bình Hưng Hòa B, Quận Bình Tân', N'0555505111', 200000.0000, CAST(N'2022-05-10T00:37:52.503' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0132', N'PB03', N'CM0011', N'Tôn Thanh ', N'Tuyết', 0, CAST(N'1977-09-17T00:00:00.000' AS DateTime), N' 83 Liên Khu 4-5, khu phố 5, Bình Hưng Hòa B, Quận Bình Tân', N'0355560887', 200000.0000, CAST(N'2022-05-10T00:37:53.030' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0133', N'PB03', N'CM0011', N'Phạm Trân ', N'Châu', 0, CAST(N'1979-07-22T00:00:00.000' AS DateTime), N'451 Bình Thành, khu phố 2, Bình Hưng Hòa B, Quận Bình Tân', N'0555535319', 200000.0000, CAST(N'2022-05-10T00:37:53.543' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0134', N'PB03', N'CM0011', N'Doãn Ngọc ', N'San', 0, CAST(N'1979-10-19T00:00:00.000' AS DateTime), N'299/10 Bình Thành, khu phố 4, Bình Hưng Hòa B, Quận Bình Tân', N'0355531173', 200000.0000, CAST(N'2022-05-10T00:37:55.657' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0135', N'PB03', N'CM0011', N'Lý Khánh Thi ', N'(Thy)', 0, CAST(N'1980-12-01T00:00:00.000' AS DateTime), N'177/27A Liên khu 4-5, Bình Hưng Hòa B, Quận Bình Tân', N'0355564372', 200000.0000, CAST(N'2022-05-10T00:37:56.103' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0136', N'PB03', N'CM0011', N'Phan Thiên ', N'Lan', 0, CAST(N'1983-02-16T00:00:00.000' AS DateTime), N'107/6 chiến lược, khu phố 16, phường Bình Trị Đông, Quận Bình Tân', N'0355547507', 200000.0000, CAST(N'2022-05-10T00:37:56.580' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0137', N'PB03', N'CM0011', N'Nghiêm Mai ', N'Hà', 0, CAST(N'1983-07-10T00:00:00.000' AS DateTime), N'112 Trương Phước Phan, phường Bình Trị Đông, Quận Bình Tân', N'0855574389', 200000.0000, CAST(N'2022-05-10T00:37:57.043' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0138', N'PB03', N'CM0011', N'Đinh Ngọc ', N'Oanh', 0, CAST(N'1984-06-16T00:00:00.000' AS DateTime), N'161/6 Trương Phước Phan, phường Bình Trị Đông, Bình Trị Đông, Quận Bình Tân', N'0855550614', 200000.0000, CAST(N'2022-05-10T00:37:57.703' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0139', N'PB03', N'CM0011', N'Tôn Tuệ ', N'Lâm', 0, CAST(N'1984-10-03T00:00:00.000' AS DateTime), N'30/4 chiến Lược khu phố 13, phường Bình Trị Đông, Bình Trị Đông, Quận Bình Tân', N'0355597741', 200000.0000, CAST(N'2022-05-11T13:02:21.983' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0140', N'PB03', N'CM0012', N'Nguyễn Thanh ', N'Hảo', 0, CAST(N'1985-01-07T00:00:00.000' AS DateTime), N'639/46 Hương Lộ 2, phường Bình Trị Đông, Bình Trị Đông, Quận Bình Tân', N'0355568842', 200000.0000, CAST(N'2022-05-11T13:02:25.150' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0141', N'PB03', N'CM0012', N'Trương Linh ', N'Chi', 0, CAST(N'1986-09-23T00:00:00.000' AS DateTime), N'103/52/15 Chiến Lược, khu phố 16, Bình Trị Đông, Quận Bình Tân', N'0555523823', 200000.0000, CAST(N'2022-05-11T13:02:25.543' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0142', N'PB03', N'CM0012', N'Huỳnh Trúc ', N'Mai', 0, CAST(N'1988-08-05T00:00:00.000' AS DateTime), N'103/72/8 Chiến Lược, KP16, Bình Trị Đông, Quận Bình Tân', N'0555571587', 200000.0000, CAST(N'2022-05-11T13:02:25.920' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0143', N'PB03', N'CM0012', N'Hoàng Kiều ', N'Minh', 0, CAST(N'1989-03-05T00:00:00.000' AS DateTime), N'137/4A Phan Anh, KP11, Bình Trị Đông, Quận Bình Tân', N'0855569134', 200000.0000, CAST(N'2022-05-11T13:02:26.313' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0144', N'PB03', N'CM0012', N'Diệp Vân ', N'Khánh', 0, CAST(N'1990-03-23T00:00:00.000' AS DateTime), N'137/77/6A Phan Anh, KP12, Bình Trị Đông, Quận Bình Tân', N'0555558429', 200000.0000, CAST(N'2022-05-11T13:02:26.770' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0145', N'PB03', N'CM0012', N'Huỳnh Yến ', N'Phượng', 0, CAST(N'1995-01-29T00:00:00.000' AS DateTime), N'22/11E Đình Nghi Xuân, KP10, Bình Trị Đông, Quận Bình Tân', N'0555506691', 200000.0000, CAST(N'2022-05-11T13:02:27.160' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0146', N'PB03', N'CM0012', N'Đặng Mỹ ', N'Huệ', 0, CAST(N'1995-02-23T00:00:00.000' AS DateTime), N'272/9 Đất Mới, khu phố 01, Bình Trị Đông, Quận Bình Tân', N'0555532385', 200000.0000, CAST(N'2022-05-11T13:02:27.550' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0147', N'PB03', N'CM0012', N'Kiều Thảo ', N'Linh', 0, CAST(N'1996-02-07T00:00:00.000' AS DateTime), N'297/25/63 Tân Hòa Đông, KP13, Bình Trị Đông, Quận Bình Tân', N'0355556157', 200000.0000, CAST(N'2022-05-11T13:02:27.970' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0148', N'PB03', N'CM0012', N'Ngô Thương ', N'Thương', 0, CAST(N'1996-05-10T00:00:00.000' AS DateTime), N'35 Phan Anh, khu phố 12, Bình Trị Đông, Quận Bình Tân', N'0755599697', 200000.0000, CAST(N'2022-05-11T13:02:28.347' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0149', N'PB03', N'CM0012', N'Doãn Vân ', N'Trinh', 0, CAST(N'1998-01-20T00:00:00.000' AS DateTime), N'473/30 Tân Hòa Đông, KP8, Bình Trị Đông, Quận Bình Tân', N'0955527744', 200000.0000, CAST(N'2022-05-11T13:02:38.677' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0150', N'PB03', N'CM0012', N'Võ Tịnh ', N'Nhi', 0, CAST(N'1999-12-13T00:00:00.000' AS DateTime), N'588/3 Tỉnh Lộ 10, KP16, Bình Trị Đông, Quận Bình Tân', N'0355521606', 200000.0000, CAST(N'2022-05-11T13:02:40.363' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0151', N'PB03', N'CM0012', N'Vũ Thành ', N'Doanh', 1, CAST(N'1977-02-09T00:00:00.000' AS DateTime), N'621/2/5 Hương Lộ 2, KP7, Bình Trị Đông, Quận Bình Tân', N'0855536313', 200000.0000, CAST(N'2022-05-11T13:02:52.533' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0152', N'PB03', N'CM0012', N'Hồ Minh ', N'Quân', 1, CAST(N'1977-02-19T00:00:00.000' AS DateTime), N'639/22 Hương Lộ 2, KP 07, Bình Trị Đông, Quận Bình Tân', N'0355572801', 200000.0000, CAST(N'2022-05-11T13:02:54.603' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0153', N'PB03', N'CM0012', N'Vũ Hiệp ', N'Hà', 1, CAST(N'1979-02-21T00:00:00.000' AS DateTime), N'68/6 Chiến Lược, KP13, Bình Trị Đông, Quận Bình Tân', N'0855554343', 200000.0000, CAST(N'2022-05-11T13:02:55.027' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0154', N'PB03', N'CM0012', N'Phạm Dũng ', N'Trí', 1, CAST(N'1981-06-29T00:00:00.000' AS DateTime), N'76 Liên khu 1-6, KP1, Bình Trị Đông, Quận Bình Tân', N'0555517753', 200000.0000, CAST(N'2022-05-11T13:02:55.490' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0155', N'PB03', N'CM0012', N'Mai Cường ', N'Thịnh', 1, CAST(N'1984-12-07T00:00:00.000' AS DateTime), N'639/73 Hương lộ 2, phường Bình Trị Đông, Quận Bình Tân', N'0855526875', 200000.0000, CAST(N'2022-05-11T13:02:55.973' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0156', N'PB03', N'CM0013', N'Hoàng Công ', N'Sơn', 1, CAST(N'1986-05-20T00:00:00.000' AS DateTime), N'276/17/14 Mã Lò, khu phố 6, Bình Trị Đông A, Quận Bình Tân', N'0755538440', 200000.0000, CAST(N'2022-05-11T13:02:56.410' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0157', N'PB03', N'CM0013', N'Phí Công ', N'Phụng', 1, CAST(N'1986-05-28T00:00:00.000' AS DateTime), N'338/132A Chiến Lược, khu phố 3, Bình Trị Đông A, Quận Bình Tân', N'0955589988', 200000.0000, CAST(N'2022-05-11T13:02:56.850' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0158', N'PB03', N'CM0013', N'Trần Minh ', N'Hưng', 1, CAST(N'1986-11-16T00:00:00.000' AS DateTime), N'413/41/14/28 Lê Văn Quới, khu phố 5, Bình Trị Đông A, Quận Bình Tân', N'0555522549', 200000.0000, CAST(N'2022-05-11T13:02:57.243' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0159', N'PB03', N'CM0013', N'Thân Hữu ', N'Khanh', 1, CAST(N'1987-09-07T00:00:00.000' AS DateTime), N'413/56 Lê Văn Quới, khu phố 5, Bình Trị Đông A, Quận Bình Tân', N'0355546536', 200000.0000, CAST(N'2022-05-11T13:02:57.687' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0160', N'PB03', N'CM0013', N'Nguyễn Anh ', N'Ðức', 1, CAST(N'1988-05-21T00:00:00.000' AS DateTime), N'24 Tây lân, Bình Trị Đông A, Quận Bình Tân', N'0355537647', 200000.0000, CAST(N'2022-05-11T13:03:17.383' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0161', N'PB03', N'CM0013', N'Mạch Tấn ', N'Phát', 1, CAST(N'1988-09-03T00:00:00.000' AS DateTime), N'158/1 Mã Lò KP2, Bình Trị Đông A, Quận Bình Tân', N'0555586887', 200000.0000, CAST(N'2022-05-11T13:03:49.750' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0162', N'PB03', N'CM0013', N'Võ Hoài ', N'Nam', 1, CAST(N'1989-04-09T00:00:00.000' AS DateTime), N'276/33 Mã Lò KP6, Bình Trị Đông A, Quận Bình Tân', N'0955517533', 200000.0000, CAST(N'2022-05-11T13:03:51.750' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0163', N'PB03', N'CM0013', N'Nguyễn Khánh ', N'Duy', 1, CAST(N'1989-05-30T00:00:00.000' AS DateTime), N'394/15 Chiến lược KP8, Bình Trị Đông A, Quận Bình Tân', N'0755517851', 200000.0000, CAST(N'2022-05-11T13:03:52.310' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0164', N'PB03', N'CM0013', N'Ngư Anh ', N'Tài', 1, CAST(N'1990-07-01T00:00:00.000' AS DateTime), N'84/1/22 Bến Lội KP7, Bình Trị Đông A, Quận Bình Tân', N'0355510567', 200000.0000, CAST(N'2022-05-11T13:03:52.837' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0165', N'PB03', N'CM0013', N'Tiêu Ðình ', N'Ðôn', 1, CAST(N'1991-09-06T00:00:00.000' AS DateTime), N'1215/5 QUỐC LỘ 1A, Bình Trị Đông B, Quận Bình Tân', N'0355558431', 200000.0000, CAST(N'2022-05-11T13:03:53.350' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0166', N'PB03', N'CM0013', N'Vũ Hải ', N'Giang', 1, CAST(N'1992-08-06T00:00:00.000' AS DateTime), N'1229 QUỐC LÔ 1A, Bình Trị Đông B, Quận Bình Tân', N'0555504843', 200000.0000, CAST(N'2022-05-11T13:04:02.933' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0167', N'PB03', N'CM0013', N'Nguyễn Quốc ', N'Thắng', 1, CAST(N'1992-08-20T00:00:00.000' AS DateTime), N'473/6/3 TỈNH LỘ 10, Bình Trị Đông B, Quận Bình Tân', N'0855594869', 200000.0000, CAST(N'2022-05-11T13:04:03.703' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0168', N'PB03', N'CM0013', N'Bùi Thanh ', N'Thuận', 1, CAST(N'1992-11-30T00:00:00.000' AS DateTime), N'504/32 KDV, Bình Trị Đông B, Quận Bình Tân', N'0755511924', 200000.0000, CAST(N'2022-05-11T13:04:04.410' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0169', N'PB03', N'CM0013', N'Hồ Quang ', N'Dương', 1, CAST(N'1994-08-13T00:00:00.000' AS DateTime), N'504/33 KDV, Bình Trị Đông B, Quận Bình Tân', N'0755572625', 200000.0000, CAST(N'2022-05-11T13:04:05.097' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0170', N'PB03', N'CM0013', N'Đỗ Nghĩa ', N'Hòa', 1, CAST(N'1995-04-30T00:00:00.000' AS DateTime), N'504/56/100 KINH DƯƠNG VƯƠNG, Bình Trị Đông B, Quận Bình Tân', N'0555578295', 200000.0000, CAST(N'2022-05-11T13:04:05.770' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0171', N'PB03', N'CM0013', N'Trần Thanh ', N'Trung', 1, CAST(N'1996-04-30T00:00:00.000' AS DateTime), N'504/56/100/8 KINH DƯƠNG VƯƠNG, Bình Trị Đông B, Quận Bình Tân', N'0555555764', 200000.0000, CAST(N'2022-05-11T13:04:27.080' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0172', N'PB03', N'CM0014', N'Hoàng Khánh ', N'Nam', 1, CAST(N'1998-03-24T00:00:00.000' AS DateTime), N'504/74/20 KINH DƯƠNG VƯƠNG, Bình Trị Đông B, Quận Bình Tân', N'0355559631', 200000.0000, CAST(N'2022-05-11T13:04:29.117' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0173', N'PB03', N'CM0014', N'Mạch Quốc ', N'Tuấn', 1, CAST(N'1998-07-29T00:00:00.000' AS DateTime), N'532/1/22/8 KHU Y TẾ KT CAO, Bình Trị Đông B, Quận Bình Tân', N'0955510965', 200000.0000, CAST(N'2022-05-11T13:04:29.597' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0174', N'PB03', N'CM0014', N'Phó Minh ', N'Hưng', 1, CAST(N'1998-09-20T00:00:00.000' AS DateTime), N'532/1/33 KHU YTKTC, Bình Trị Đông B, Quận Bình Tân', N'0555599768', 200000.0000, CAST(N'2022-05-11T13:04:30.080' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0175', N'PB03', N'CM0014', N'Huỳnh Quang ', N'Tuấn', 1, CAST(N'2000-08-06T00:00:00.000' AS DateTime), N'532/21/49/1C KHU YTKTC, Bình Trị Đông B, Quận Bình Tân', N'0355512442', 200000.0000, CAST(N'2022-05-11T13:04:30.523' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0176', N'PB03', N'CM0014', N'Nguyễn Ðăng ', N'Ðạt', 1, CAST(N'1977-02-09T00:00:00.000' AS DateTime), N'532/28/34/14 KHU YTKTC, Bình Trị Đông B, Quận Bình Tân', N'0355563565', 200000.0000, CAST(N'2022-05-11T13:04:30.987' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0177', N'PB03', N'CM0014', N'Huỳnh Quang ', N'Lộc', 1, CAST(N'1977-02-19T00:00:00.000' AS DateTime), N'541/14/1 TỈNH LỘ 10, Bình Trị Đông B, Quận Bình Tân', N'0355567106', 200000.0000, CAST(N'2022-05-11T13:04:31.503' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0178', N'PB03', N'CM0014', N'Vũ Duy ', N'Bảo', 1, CAST(N'1979-02-21T00:00:00.000' AS DateTime), N'541/16 TỈNH LÔ 10, Bình Trị Đông B, Quận Bình Tân', N'0355561338', 200000.0000, CAST(N'2022-05-11T13:04:32.020' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0179', N'PB03', N'CM0014', N'Nguyễn Thanh ', N'Phi', 1, CAST(N'1981-06-29T00:00:00.000' AS DateTime), N'541/26 TỈNH LỘ 10, Bình Trị Đông B, Quận Bình Tân', N'0355527884', 200000.0000, CAST(N'2022-05-11T13:04:32.503' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0180', N'PB03', N'CM0014', N'Ngô Bảo ', N'Chấn', 1, CAST(N'1984-12-07T00:00:00.000' AS DateTime), N'541/9 TỈNH LỘ 10, Bình Trị Đông B, Quận Bình Tân', N'0555513344', 200000.0000, CAST(N'2022-05-11T13:04:33.043' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0181', N'PB03', N'CM0014', N'Diệp Vĩnh ', N'Thụy', 1, CAST(N'1986-05-20T00:00:00.000' AS DateTime), N'560 HỒ HỌC LÃM, Bình Trị Đông B, Quận Bình Tân', N'0355503949', 200000.0000, CAST(N'2022-05-11T13:04:33.950' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0182', N'PB03', N'CM0014', N'Nguyễn Nhật ', N'Duy', 1, CAST(N'1986-05-28T00:00:00.000' AS DateTime), N'561/1/1 TỈNH LỘ 10, Bình Trị Đông B, Quận Bình Tân', N'0555578837', 200000.0000, CAST(N'2022-05-11T13:04:34.543' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0183', N'PB03', N'CM0014', N'Nguyễn Gia ', N'Kiệt', 1, CAST(N'1986-11-16T00:00:00.000' AS DateTime), N'574/104/5 SINCO, Bình Trị Đông B, Quận Bình Tân', N'0555553319', 200000.0000, CAST(N'2022-05-11T13:05:02.820' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0184', N'PB03', N'CM0014', N'Kiều Quang ', N'Minh', 1, CAST(N'1987-09-07T00:00:00.000' AS DateTime), N'574/106/1 SINCO, Bình Trị Đông B, Quận Bình Tân', N'0355506999', 200000.0000, CAST(N'2022-05-11T13:05:04.613' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0185', N'PB03', N'CM0014', N'Đỗ Công ', N'Phụng', 1, CAST(N'1988-05-21T00:00:00.000' AS DateTime), N'574/15/18/10 SINCO, Bình Trị Đông B, Quận Bình Tân', N'0755523515', 200000.0000, CAST(N'2022-05-11T13:05:05.170' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0186', N'PB03', N'CM0014', N'Doãn Huy ', N'Lĩnh', 1, CAST(N'1988-09-03T00:00:00.000' AS DateTime), N'574/15/27 SINCO, Bình Trị Đông B, Quận Bình Tân', N'0755541801', 200000.0000, CAST(N'2022-05-11T13:05:05.663' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0187', N'PB03', N'CM0014', N'Trương Lạc ', N'Phúc', 1, CAST(N'1989-04-09T00:00:00.000' AS DateTime), N'574/15/55/15/3 SINCO, Bình Trị Đông B, Quận Bình Tân', N'0355577897', 200000.0000, CAST(N'2022-05-11T13:05:15.613' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0188', N'PB03', N'CM0014', N'Trang Trọng ', N'Việt', 1, CAST(N'1989-05-30T00:00:00.000' AS DateTime), N'574/23 SINCO, Bình Trị Đông B, Quận Bình Tân', N'0355516204', 200000.0000, CAST(N'2022-05-11T13:05:16.183' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0189', N'PB03', N'CM0014', N'Mạc Ðức ', N'Bằng', 1, CAST(N'1990-07-01T00:00:00.000' AS DateTime), N'574/70 SINCO, Bình Trị Đông B, Quận Bình Tân', N'0555545649', 200000.0000, CAST(N'2022-05-11T13:05:28.340' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0190', N'PB03', N'CM0015', N'Nguyễn Xuân ', N'Ninh', 1, CAST(N'1991-09-06T00:00:00.000' AS DateTime), N'576/14/21 HỒ HỌC LÃM, Bình Trị Đông B, Quận Bình Tân', N'0555508292', 200000.0000, CAST(N'2022-05-11T13:05:28.960' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0191', N'PB03', N'CM0015', N'Tạ Ðại ', N'Hành', 1, CAST(N'1992-08-06T00:00:00.000' AS DateTime), N'576/23 HỒ HỌC LÃM, Bình Trị Đông B, Quận Bình Tân', N'0955573476', 200000.0000, CAST(N'2022-05-11T13:05:29.767' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0192', N'PB03', N'CM0015', N'Nguyễn Quang ', N'Thắng', 1, CAST(N'1992-08-20T00:00:00.000' AS DateTime), N'606/1/2 HỒ HỌC LÃM, Bình Trị Đông B, Quận Bình Tân', N'0355582573', 200000.0000, CAST(N'2022-05-11T13:05:30.397' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0193', N'PB03', N'CM0015', N'Nguyễn Ðạt ', N'Hòa', 1, CAST(N'1992-11-30T00:00:00.000' AS DateTime), N'827/19 TỈNH LỘ 10, Bình Trị Đông B, Quận Bình Tân', N'0555548702', 200000.0000, CAST(N'2022-05-11T13:05:31.043' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0194', N'PB03', N'CM0015', N'Lê Khánh ', N'An', 1, CAST(N'1994-08-13T00:00:00.000' AS DateTime), N'827/52/1 TỈNH LỘ 10, Bình Trị Đông B, Quận Bình Tân', N'0755503313', 200000.0000, CAST(N'2022-05-11T13:05:48.017' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0195', N'PB03', N'CM0015', N'Lê Quang ', N'Thuận', 1, CAST(N'1995-04-30T00:00:00.000' AS DateTime), N'11 TÊN LỬA, Bình Trị Đông B, Quận Bình Tân', N'0355505726', 200000.0000, CAST(N'2022-05-11T13:05:50.347' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0196', N'PB03', N'CM0015', N'Bùi Nhật ', N'Minh', 1, CAST(N'1996-04-30T00:00:00.000' AS DateTime), N'1074/28/3 Tỉnh Lộ 10, Khu phố 7, phường Tân Tạo, Quận Bình Tân', N'0555522709', 200000.0000, CAST(N'2022-05-11T13:05:51.013' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0197', N'PB03', N'CM0015', N'Bùi Kiên ', N'Trung', 1, CAST(N'1998-03-24T00:00:00.000' AS DateTime), N'1117/15 Tỉnh Lộ 10, Tân Tạo, Quận Bình Tân', N'0955526222', 200000.0000, CAST(N'2022-05-11T13:05:51.637' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0198', N'PB03', N'CM0015', N'Quyền Minh ', N'Nhân', 1, CAST(N'1998-07-29T00:00:00.000' AS DateTime), N'234 Tỉnh Lộ 10, Tân Tạo, Quận Bình Tân', N'0855569570', 200000.0000, CAST(N'2022-05-11T13:05:52.247' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0199', N'PB03', N'CM0015', N'Giang Nam ', N'Tú', 1, CAST(N'1998-09-20T00:00:00.000' AS DateTime), N'386 Trần Văn Giàu, Khu phố 8, phường Tân Tạo, Quận Bình Tân', N'0855562662', 200000.0000, CAST(N'2022-05-11T13:05:52.807' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0200', N'PB03', N'CM0015', N'Dương Xuân ', N'Lộc', 1, CAST(N'2000-08-06T00:00:00.000' AS DateTime), N'827/19 TỈNH LỘ 10, Bình Trị Đông B, Quận Bình Tân', N'0555587486', 200000.0000, CAST(N'2022-05-11T13:06:00.153' AS DateTime))
GO
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0201', N'PB03', N'CM0015', N'Trần Ngọc ', N'Tuấn', 1, CAST(N'1971-02-04T00:00:00.000' AS DateTime), N'59 Tinh Lộ, Tân Tạo, Quận Bình Tân', N'0855523867', 200000.0000, CAST(N'2022-05-11T13:06:01.133' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0202', N'PB03', N'CM0015', N'Vương Gia ', N'Vinh', 1, CAST(N'1971-05-12T00:00:00.000' AS DateTime), N'110/9 Trần Đại Nghĩa, KP6, Tân Tạo A, Quận Bình Tân', N'0355565313', 200000.0000, CAST(N'2022-05-11T13:06:01.723' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0203', N'PB03', N'CM0015', N'Đinh Việt ', N'Khải', 1, CAST(N'1972-02-06T00:00:00.000' AS DateTime), N'15 Lê Đình Cẩn, Tân Tạo, Quận Bình Tân', N'0955535203', 200000.0000, CAST(N'2022-05-11T13:06:02.407' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0204', N'PB03', N'CM0015', N'Thủy Anh ', N'Tài', 1, CAST(N'1972-02-17T00:00:00.000' AS DateTime), N'1159/7 Tỉnh Lộ 10, Tân Tạo, Quận Bình Tân', N'0355501197', 200000.0000, CAST(N'2022-05-11T13:06:03.013' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0205', N'PB03', N'CM0006', N'Lê Thế ', N'Tường', 1, CAST(N'1973-06-10T00:00:00.000' AS DateTime), N'9 52A, phường Tân Tạo, Tân Tạo, Quận Bình Tân', N'0555520821', 200000.0000, CAST(N'2022-05-11T13:06:03.717' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0206', N'PB03', N'CM0006', N'Nguyễn Khánh ', N'Hội', 1, CAST(N'1973-06-19T00:00:00.000' AS DateTime), N'25/64 Hồ Văn Long, Tân Tạo, Quận Bình Tân', N'0355514749', 200000.0000, CAST(N'2022-05-11T13:06:04.530' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0207', N'PB03', N'CM0006', N'Tô Thế ', N'Minh', 1, CAST(N'1973-09-26T00:00:00.000' AS DateTime), N'8 phường Tân Tạo A, Tân Tạo A, Quận Bình Tân', N'0555524755', 200000.0000, CAST(N'2022-05-11T13:06:05.240' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0208', N'PB03', N'CM0006', N'Bạch Vương ', N'Gia', 1, CAST(N'1973-11-29T00:00:00.000' AS DateTime), N'103 Trần Thanh Mại, Kp3, Tân Tạo A, Quận Bình Tân', N'0855517542', 200000.0000, CAST(N'2022-05-11T13:06:05.963' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0209', N'PB03', N'CM0006', N'Đặng Thụy ', N'Long', 1, CAST(N'1974-08-07T00:00:00.000' AS DateTime), N'108 Trần Thanh Mại, Tân Tạo A, Quận Bình Tân', N'0555541786', 200000.0000, CAST(N'2022-05-11T13:06:12.203' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0210', N'PB03', N'CM0006', N'Đào Ðông ', N'Nguyên', 1, CAST(N'1975-08-28T00:00:00.000' AS DateTime), N'110/9 Trần Đại Nghĩa, KP6, Tân Tạo A, Quận Bình Tân', N'0555556594', 200000.0000, CAST(N'2022-05-11T13:06:13.247' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0211', N'PB03', N'CM0006', N'Thân Quảng ', N'Thông', 1, CAST(N'1978-04-23T00:00:00.000' AS DateTime), N'115 ĐS5 KP2, Tân Tạo A, Quận Bình Tân', N'0555585148', 200000.0000, CAST(N'2022-05-11T13:06:14.457' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0212', N'PB03', N'CM0006', N'Nguyễn Quang ', N'Hưng', 1, CAST(N'1978-07-26T00:00:00.000' AS DateTime), N'1166/42/16/5, Quốc lộ 1A, KP1, Tân Tạo A, Quận Bình Tân', N'0855591989', 200000.0000, CAST(N'2022-05-11T13:06:15.193' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0213', N'PB03', N'CM0016', N'Chung Minh ', N'Thiện', 1, CAST(N'1979-11-12T00:00:00.000' AS DateTime), N'1166/60/75, Quốc lộ 1A, Tân Tạo A, Quận Bình Tân', N'0355502680', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0214', N'PB03', N'CM0016', N'Mã Minh ', N'Kỳ', 1, CAST(N'1983-04-27T00:00:00.000' AS DateTime), N'117 Trần Thanh Mại, Kp3, Tân Tạo A, Quận Bình Tân', N'0355590603', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0215', N'PB03', N'CM0016', N'Đỗ Xuân ', N'Ninh', 1, CAST(N'1983-07-14T00:00:00.000' AS DateTime), N'131 Cầu Kinh, Kp7, Tân Tạo A, Quận Bình Tân', N'0555550556', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0216', N'PB03', N'CM0016', N'Phùng Ðức ', N'Tuệ', 1, CAST(N'1984-04-07T00:00:00.000' AS DateTime), N'1455/2 Tỉnh Lộ 10, P. Tân Tạo A, Bình Tân, Tân Tạo A, Quận Bình Tân', N'0955533623', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0217', N'PB03', N'CM0016', N'Lý Lam ', N'Phương', 1, CAST(N'1984-07-21T00:00:00.000' AS DateTime), N'147 Cầu Kinh, Kp4, Tân Tạo A, Quận Bình Tân', N'0555549586', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0218', N'PB03', N'CM0016', N'Nguyễn Quốc ', N'Vinh', 1, CAST(N'1989-03-09T00:00:00.000' AS DateTime), N'1659/1 Tỉnh Lộ 10, kp5, Tân Tạo A, Quận Bình Tân', N'0955518297', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0219', N'PB03', N'CM0016', N'Vũ Phước ', N'Thiện', 1, CAST(N'1989-10-10T00:00:00.000' AS DateTime), N'169 Trần Thanh Mại, Kp3, Tân Tạo A, Quận Bình Tân', N'0555555112', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0220', N'PB03', N'CM0016', N'Nguyễn Tuấn ', N'Linh', 1, CAST(N'1989-11-26T00:00:00.000' AS DateTime), N'1R Kinh 2, KP4, Tân Tạo A, Quận Bình Tân', N'0555556579', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0221', N'PB04', N'CM0013', N'Lý Hồng ', N'Sơn', 1, CAST(N'1990-09-16T00:00:00.000' AS DateTime), N'202 Trần Thanh Mại, Tân Tạo A, Quận Bình Tân', N'0855542191', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0222', N'PB04', N'CM0018', N'Lưu Giang ', N'Thiên', 1, CAST(N'1992-04-19T00:00:00.000' AS DateTime), N'27/3/6/A Nguyễn Văn Cự, Kp7, Tân Tạo A, Quận Bình Tân', N'0855581490', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0223', N'PB04', N'CM0018', N'Trịnh Quang ', N'Nhân', 1, CAST(N'1997-08-15T00:00:00.000' AS DateTime), N'30IA 3, Kp3, Tân Tạo A, Quận Bình Tân', N'0855507384', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0224', N'PB04', N'CM0018', N'Tiêu Bình ', N'Quân', 1, CAST(N'1998-09-28T00:00:00.000' AS DateTime), N'32 Lộ Tẻ, Kp3, Tân Tạo A, Quận Bình Tân', N'0355599630', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0225', N'PB04', N'CM0018', N'Vĩnh Lâm ', N'Ðông', 1, CAST(N'1998-11-22T00:00:00.000' AS DateTime), N'34 ĐS4, KP4, Tân Tạo A, Quận Bình Tân', N'0355577308', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0226', N'PB04', N'CM0018', N'Võ Xuân ', N'Hòa', 1, CAST(N'1970-07-17T00:00:00.000' AS DateTime), N'34 Lộ Tẻ, KP3, Tân Tạo A, Quận Bình Tân', N'0355515863', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0227', N'PB04', N'CM0018', N'Nguyễn Chí ', N'Dũng', 1, CAST(N'1971-11-27T00:00:00.000' AS DateTime), N'42/41 Trần Đại Nghĩa, Kp6, Tân Tạo A, Quận Bình Tân', N'0855538278', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0228', N'PB04', N'CM0018', N'Ngư Tường ', N'Anh', 1, CAST(N'1972-10-25T00:00:00.000' AS DateTime), N'4324/10/9/7 Nguyễn Cửu Phú, KP4, Tân Tạo A, Quận Bình Tân', N'0855517195', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0229', N'PB04', N'CM0018', N'An Bình ', N'An', 1, CAST(N'1974-11-10T00:00:00.000' AS DateTime), N'4394/1 Nguyễn Cửu Phú, KP4, Tân Tạo A, Quận Bình Tân', N'0555552545', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0230', N'PB04', N'CM0018', N'Đặng Phụng ', N'Việt', 1, CAST(N'1976-12-21T00:00:00.000' AS DateTime), N'44 7, Kp3, Tân Tạo A, Quận Bình Tân', N'0555534584', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0231', N'PB04', N'CM0018', N'Trang Sương ', N'Sương', 0, CAST(N'1977-12-16T00:00:00.000' AS DateTime), N'4430/2/2 NCP, Tân Tạo A, Quận Bình Tân', N'0355529247', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0232', N'PB04', N'CM0018', N'Trầm Ngọc ', N'Nhi', 0, CAST(N'1981-09-20T00:00:00.000' AS DateTime), N'4435 Nguyễn Cửu Phú, Tân Tạo A, Quận Bình Tân', N'0555553687', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0233', N'PB04', N'CM0018', N'Lâm Mai ', N'Hương', 0, CAST(N'1982-01-20T00:00:00.000' AS DateTime), N'50A/5 1, KP5, Tân Tạo A, Quận Bình Tân', N'0555592585', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0234', N'PB04', N'CM0018', N'Diệp Thy ', N'Vân', 0, CAST(N'1983-02-22T00:00:00.000' AS DateTime), N'439/27/43 Hồ Học Lãm, An Lạc, Quận Bình Tân', N'0555507395', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0235', N'PB04', N'CM0018', N'An Nguyệt ', N'Uyển', 0, CAST(N'1984-02-12T00:00:00.000' AS DateTime), N'500 Hồ Học Lãm, An Lạc, Quận Bình Tân', N'0855586644', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0236', N'PB04', N'CM0018', N'Đoàn Bích ', N'Phượng', 0, CAST(N'1984-10-01T00:00:00.000' AS DateTime), N'510 Hồ Học Lãm, An Lạc, Quận Bình Tân', N'0955594166', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0237', N'PB04', N'CM0018', N'Đỗ Thanh ', N'Tuyết', 0, CAST(N'1984-10-30T00:00:00.000' AS DateTime), N'574/16/6 Kinh Dương Vương, An Lạc, Quận Bình Tân', N'0555503218', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0238', N'PB04', N'CM0018', N'Quang Hồng ', N'Oanh', 0, CAST(N'1985-04-09T00:00:00.000' AS DateTime), N'664 Kinh Dương Vương, An Lạc, Quận Bình Tân', N'0355526610', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0239', N'PB04', N'CM0018', N'Hoàng Thu ', N'Sương', 0, CAST(N'1987-05-13T00:00:00.000' AS DateTime), N'929 Quốc lộ 1, An Lạc, Quận Bình Tân', N'0555578842', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0240', N'PB04', N'CM0018', N'Thảo Diễm ', N'Thư', 0, CAST(N'1987-07-02T00:00:00.000' AS DateTime), N'94 1 Khu phố 3, An Lạc, Quận Bình Tân', N'0355544903', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0241', N'PB05', N'CM0001', N'Phan Thanh ', N'Thảo', 0, CAST(N'1987-08-19T00:00:00.000' AS DateTime), N'Chợ khu phố 2, phường An Lạc, An Lạc, Quận Bình Tân', N'0555531486', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0242', N'PB05', N'CM0001', N'Trần Bích ', N'Hải', 0, CAST(N'1988-06-02T00:00:00.000' AS DateTime), N'Chung cư 35 Hồ Học Lãm, An Lạc, Quận Bình Tân', N'0555557355', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0243', N'PB05', N'CM0001', N'Tống Tuyết ', N'Vy', 0, CAST(N'1990-10-08T00:00:00.000' AS DateTime), N'Chung cư Ehome3 103 Hồ Học Lãm, phường An Lạc, An Lạc, Quận Bình Tân', N'0755503629', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0244', N'PB05', N'CM0001', N'Diệp Thục ', N'Anh', 0, CAST(N'1992-03-26T00:00:00.000' AS DateTime), N'13 Khu dân cư Nam Long, An Lạc, Quận Bình Tân', N'0955564779', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0245', N'PB05', N'CM0001', N'Giang Trâm ', N'Oanh', 0, CAST(N'1993-03-12T00:00:00.000' AS DateTime), N'2A Khu phố 3, An Lạc, Quận Bình Tân', N'0855594985', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0246', N'PB05', N'CM0001', N'Diệp Uyên Thi ', N'Thy', 0, CAST(N'1995-04-02T00:00:00.000' AS DateTime), N' 230 Hồ Học Lãm, An Lạc, Quận Bình Tân', N'0355527812', 200000.0000, CAST(N'2022-05-12T12:59:23.670' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0247', N'PB05', N'CM0001', N'Vũ Phương ', N'Thùy', 0, CAST(N'1996-08-24T00:00:00.000' AS DateTime), N' 349 An Dương Vương, An Lạc, Quận Bình Tân', N'0355512986', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0248', N'PB05', N'CM0001', N'Nguyễn Ngọc ', N'Huệ', 0, CAST(N'1997-06-12T00:00:00.000' AS DateTime), N' 38 Nguyễn Hới, An Lạc, Quận Bình Tân', N'0855560470', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0249', N'PB05', N'CM0001', N'Nguyễn Ngọc ', N'Trâm', 0, CAST(N'1998-09-10T00:00:00.000' AS DateTime), N' 55 Phan Đình Thông, An Lạc, Quận Bình Tân', N'0555530720', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0250', N'PB05', N'CM0001', N'Võ Huệ ', N'My', 0, CAST(N'1999-08-14T00:00:00.000' AS DateTime), N'Khu phố 2,3,4 phường An Lạc, An Lạc, Quận Bình Tân', N'0555510771', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0251', N'PB05', N'CM0001', N'Trầm Lan ', N'Phương', 0, CAST(N'1970-07-17T00:00:00.000' AS DateTime), N'26 Tên Lửa, An Lạc A, Quận Bình Tân', N'0355565726', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0252', N'PB05', N'CM0001', N'Phan Thúy ', N'Anh', 0, CAST(N'1971-11-27T00:00:00.000' AS DateTime), N'40 Tên Lửa, An Lạc A, Quận Bình Tân', N'0355522538', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0253', N'PB05', N'CM0001', N'Úc Kim ', N'Thảo', 0, CAST(N'1972-10-25T00:00:00.000' AS DateTime), N'100A Tên Lửa, kp 1, An Lạc A, Quận Bình Tân', N'0555518639', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0254', N'PB05', N'CM0001', N'Hồ Hồng ', N'Mai', 0, CAST(N'1974-11-10T00:00:00.000' AS DateTime), N'148 1, kp 3, An Lạc A, Quận Bình Tân', N'0355538766', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0255', N'PB05', N'CM0001', N'Nghiêm Kiều ', N'Nguyệt', 0, CAST(N'1976-12-21T00:00:00.000' AS DateTime), N'18 Tên Lửa, kp 1, An Lạc A, Quận Bình Tân', N'0355545301', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0256', N'PB05', N'CM0001', N'Dương Hương ', N'Lâm', 0, CAST(N'1977-12-16T00:00:00.000' AS DateTime), N'3 2, kp 2, An Lạc A, Quận Bình Tân', N'0555509577', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0257', N'PB05', N'CM0001', N'Lê Liên ', N'Phương', 0, CAST(N'1981-09-20T00:00:00.000' AS DateTime), N'36A Kinh Dương Vương, kp 1, An Lạc A, Quận Bình Tân', N'0355516933', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0258', N'PB05', N'CM0001', N'Dương Kiều ', N'Hoa', 0, CAST(N'1982-01-20T00:00:00.000' AS DateTime), N'4 6D, kp 8, An Lạc A, Quận Bình Tân', N'0955524288', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0259', N'PB05', N'CM0001', N'Nguyễn Diệu ', N'Ái', 0, CAST(N'1983-02-22T00:00:00.000' AS DateTime), N'460/2 Kinh Dương Vương, kp 1, An Lạc A, Quận Bình Tân', N'0555508375', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0260', N'PB05', N'CM0001', N'Lý Thanh ', N'Tâm', 0, CAST(N'1984-02-12T00:00:00.000' AS DateTime), N'54B Tạ Mỹ Duật, kp 3, An Lạc A, Quận Bình Tân', N'0355536518', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0261', N'PB06', N'CM0005', N'Vũ Chí ', N'Anh', 1, CAST(N'1984-10-01T00:00:00.000' AS DateTime), N'86/9 Tên lửa, , kp 1, An Lạc A, Quận Bình Tân', N'0355523472', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0262', N'PB06', N'CM0005', N'Phan Trọng ', N'Kiên', 1, CAST(N'1984-10-30T00:00:00.000' AS DateTime), N'150/49/2 26/3, Bình Hưng Hòa, Quận Bình Tân', N'0955527603', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0263', N'PB06', N'CM0005', N'Nguyễn Bảo ', N'Toàn', 1, CAST(N'1985-04-09T00:00:00.000' AS DateTime), N'16/23/16/6/14 9, kp 13, Bình Hưng Hòa, Quận Bình Tân', N'0955531105', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0264', N'PB06', N'CM0005', N'Phan Hữu ', N'Tài', 1, CAST(N'1987-05-13T00:00:00.000' AS DateTime), N'16/23/4/1 9, kp 13, Bình Hưng Hòa, Quận Bình Tân', N'0855523697', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0265', N'PB06', N'CM0005', N'Nguyễn Vĩnh ', N'Ân', 1, CAST(N'1987-07-02T00:00:00.000' AS DateTime), N'16/23/6/4 9, Bình Hưng Hòa, Quận Bình Tân', N'0555583179', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0266', N'PB06', N'CM0005', N'Diệp Quốc ', N'Quân', 1, CAST(N'1987-08-19T00:00:00.000' AS DateTime), N'191 Phạm Đăng Giảng, Bình Hưng Hòa, Quận Bình Tân', N'0555544685', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0267', N'PB06', N'CM0005', N'Ngô Ðức ', N'Khiêm', 1, CAST(N'1988-06-02T00:00:00.000' AS DateTime), N'219/51 5, Bình Hưng Hòa, Quận Bình Tân', N'0355581185', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0268', N'PB06', N'CM0005', N'Phạm Huy ', N'Lâm', 1, CAST(N'1990-10-08T00:00:00.000' AS DateTime), N'246/48 26/3, Bình Hưng Hòa, Quận Bình Tân', N'0855521826', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0269', N'PB06', N'CM0005', N'Hồ Quốc ', N'Khánh', 1, CAST(N'1992-03-26T00:00:00.000' AS DateTime), N'246/49/2 26/3, Bình Hưng Hòa, Quận Bình Tân', N'0755562401', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0270', N'PB06', N'CM0005', N'Thạch Phúc ', N'Thịnh', 1, CAST(N'1993-03-12T00:00:00.000' AS DateTime), N'246/6 26/3, Bình Hưng Hòa, Quận Bình Tân', N'0755518357', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0271', N'PB06', N'CM0005', N'Phí Quốc ', N'Bình', 1, CAST(N'1995-04-02T00:00:00.000' AS DateTime), N'257 Phạm Đăng Giảng, Bình Hưng Hòa, Quận Bình Tân', N'0955598192', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0272', N'PB06', N'CM0005', N'Đặng Chí ', N'Bảo', 1, CAST(N'1996-08-24T00:00:00.000' AS DateTime), N'403/2/9 hương lộ 3, Bình Hưng Hòa, Quận Bình Tân', N'0955516902', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0273', N'PB06', N'CM0005', N'Lê Minh ', N'Trung', 1, CAST(N'1997-06-12T00:00:00.000' AS DateTime), N'435/1 Hương lộ 3, Bình Hưng Hòa, Quận Bình Tân', N'0355575588', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0274', N'PB06', N'CM0005', N'Phí Bá ', N'Cường', 1, CAST(N'1998-09-10T00:00:00.000' AS DateTime), N'435/1 Hương Lộ 3, kp 19, Bình Hưng Hòa, Quận Bình Tân', N'0355514587', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0275', N'PB06', N'CM0005', N'Lý Thiện ', N'Phước', 1, CAST(N'1999-08-14T00:00:00.000' AS DateTime), N'481 quốc lộ 1, Bình Hưng Hòa, Quận Bình Tân', N'0355557577', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0276', N'PB06', N'CM0005', N'Trần Ðại ', N'Ngọc', 1, CAST(N'1970-10-27T00:00:00.000' AS DateTime), N'50/17/17A 5, Bình Hưng Hòa, Quận Bình Tân', N'0355507333', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0277', N'PB06', N'CM0005', N'Chung Quang ', N'Dũng', 1, CAST(N'1972-05-30T00:00:00.000' AS DateTime), N'557/63 Hương lộ 3, Bình Hưng Hòa, Quận Bình Tân', N'0555578508', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0278', N'PB06', N'CM0005', N'Tạ Công ', N'Tuấn', 1, CAST(N'1972-06-05T00:00:00.000' AS DateTime), N'557/63 Hương lộ 3, kp 9, Bình Hưng Hòa, Quận Bình Tân', N'0355515150', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0279', N'PB06', N'CM0005', N'Mã Gia ', N'Uy', 1, CAST(N'1973-04-24T00:00:00.000' AS DateTime), N'638/47 Lê Trọng Tấn, Bình Hưng Hòa, Quận Bình Tân', N'0755557839', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0280', N'PB06', N'CM0005', N'Châu Việt ', N'Phong', 1, CAST(N'1973-09-16T00:00:00.000' AS DateTime), N'72 10, Bình Hưng Hòa, Quận Bình Tân', N'0355548244', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0281', N'PB07', N'CM0005', N'Lưu Nhật ', N'Hồng', 1, CAST(N'1975-03-13T00:00:00.000' AS DateTime), N'754/3 Tân Kỳ - Tân Quý, Bình Hưng Hòa, Quận Bình Tân', N'0355565417', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0282', N'PB07', N'CM0017', N'Vũ Long ', N'Vịnh', 1, CAST(N'1976-01-08T00:00:00.000' AS DateTime), N'79/71/3 4, Bình Hưng Hòa, Quận Bình Tân', N'0355530836', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0283', N'PB07', N'CM0017', N'Phó Vĩnh ', N'Ân', 1, CAST(N'1976-05-23T00:00:00.000' AS DateTime), N'80 18, Bình Hưng Hòa, Quận Bình Tân', N'0355578714', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0284', N'PB07', N'CM0017', N'Phan Duy ', N'Tâm', 1, CAST(N'1978-08-18T00:00:00.000' AS DateTime), N'80 18, kp 2, Bình Hưng Hòa, Quận Bình Tân', N'0555500393', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0285', N'PB07', N'CM0017', N'Nguyễn Tuấn ', N'Khanh', 1, CAST(N'1984-03-05T00:00:00.000' AS DateTime), N'99 Phạm Đăng Giảng, P. Bình Hưng Hòa, Bình Tân, Bình Hưng Hòa, Quận Bình Tân', N'0355538805', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0286', N'PB07', N'CM0017', N'Triệu Ðình ', N'Phú', 1, CAST(N'1987-06-18T00:00:00.000' AS DateTime), N'9A 13, P. Bình Hưng Hòa, Q. Bình Tân, Bình Hưng Hòa, Quận Bình Tân', N'0555531709', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0287', N'PB07', N'CM0017', N'Dương Bách ', N'Du', 1, CAST(N'1988-01-05T00:00:00.000' AS DateTime), N'Chung Cư Gia Phú, Bình Hưng Hòa, Quận Bình Tân', N'0355523243', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0288', N'PB07', N'CM0017', N'Úc Thụ ', N'Nhân', 1, CAST(N'1989-01-22T00:00:00.000' AS DateTime), N'Kế 69/52 3, Bình Hưng Hòa, Quận Bình Tân', N'0355588856', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0289', N'PB07', N'CM0017', N'Bùi Ngọc ', N'Tuấn', 1, CAST(N'1989-02-17T00:00:00.000' AS DateTime), N'Phòng khám Thiên Phước, Bình Hưng Hòa, Quận Bình Tân', N'0355574538', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0290', N'PB07', N'CM0017', N'Chu Thanh ', N'Hậu', 1, CAST(N'1990-08-08T00:00:00.000' AS DateTime), N'110/2/16 4, KP3, P Bình Hưng Hòa A, Q Bình Tân, Bình Hưng Hòa A, Quận Bình Tân', N'0355566935', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0291', N'PB07', N'CM0017', N'Nguyễn Tuấn ', N'Minh', 1, CAST(N'1991-06-14T00:00:00.000' AS DateTime), N'38 10 khu phố 15, Bình Hưng Hòa A, Quận Bình Tân', N'0555579672', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0292', N'PB07', N'CM0017', N'Phạm Chí ', N'Sơn', 1, CAST(N'1993-08-26T00:00:00.000' AS DateTime), N'Gò Xoài khu phố 3, Bình Hưng Hòa A, Quận Bình Tân', N'0355581982', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0293', N'PB07', N'CM0017', N'Ngô Cát ', N'Uy', 1, CAST(N'1993-09-19T00:00:00.000' AS DateTime), N'Lê Văn Quới khu phố 24, Bình Hưng Hòa A, Quận Bình Tân', N'0855565605', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0294', N'PB07', N'CM0017', N'Bùi An ', N'Tường', 1, CAST(N'1994-08-19T00:00:00.000' AS DateTime), N'Lê Văn Quới khu phố 26, Bình Hưng Hòa A, Quận Bình Tân', N'0555583806', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0295', N'PB07', N'CM0017', N'Nguyễn Ðức ', N'Khải', 1, CAST(N'1994-11-30T00:00:00.000' AS DateTime), N'Liên khu 8-9 khu phố 8, Bình Hưng Hòa A, Quận Bình Tân', N'0755538438', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0296', N'PB07', N'CM0017', N'Quyền Chí ', N'Công', 1, CAST(N'1996-04-20T00:00:00.000' AS DateTime), N'Miếu Gò Xoài khu phố 11, Bình Hưng Hòa A, Quận Bình Tân', N'0355519501', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0297', N'PB07', N'CM0017', N'Chung Thanh ', N'Ðoàn', 1, CAST(N'1997-06-18T00:00:00.000' AS DateTime), N'12 khu phố 15, Bình Hưng Hòa A, Quận Bình Tân', N'0355536168', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0298', N'PB07', N'CM0017', N'Võ Uy ', N'Phong', 1, CAST(N'1998-06-25T00:00:00.000' AS DateTime), N'12 khu phố 25, Bình Hưng Hòa A, Quận Bình Tân', N'0855549426', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0299', N'PB07', N'CM0017', N'Hồ Chính ', N'Thuận', 1, CAST(N'1999-04-05T00:00:00.000' AS DateTime), N'1A khu phố 26, Bình Hưng Hòa A, Quận Bình Tân', N'0955597521', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
INSERT [dbo].[NHANVIEN] ([MaNV], [MaPB], [MaCM], [HoNV], [TenNV], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [LuongTheoNgay], [NgaySuaDoi]) VALUES (N'NV0300', N'PB07', N'CM0017', N'Thủy Thiện ', N'Khiêm', 1, CAST(N'2001-11-12T00:00:00.000' AS DateTime), N'4 khu phố 9, Bình Hưng Hòa A, Quận Bình Tân', N'0855517195', 200000.0000, CAST(N'2022-05-05T13:34:09.113' AS DateTime))
GO
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0021', N'CT004', N'CV005', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T17:07:52.020' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0022', N'CT004', N'CV005', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T17:07:52.027' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0023', N'CT004', N'CV005', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T17:07:52.017' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0024', N'CT004', N'CV005', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T17:07:52.020' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0025', N'CT004', N'CV005', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T17:07:52.030' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0026', N'CT004', N'CV005', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T17:07:52.027' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0027', N'CT004', N'CV005', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T17:07:52.027' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0028', N'CT004', N'CV005', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T17:07:52.030' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0029', N'CT004', N'CV005', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T17:07:52.030' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0030', N'CT004', N'CV005', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T17:07:52.010' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0031', N'CT001', N'CV002', CAST(N'2022-05-09T00:00:00.000' AS DateTime), CAST(N'2022-05-09T14:31:43.760' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0032', N'CT004', N'CV005', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T17:07:52.020' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0033', N'CT004', N'CV005', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T17:07:52.030' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0035', N'CT004', N'CV005', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T17:07:52.023' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0036', N'CT004', N'CV005', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T17:07:52.023' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0037', N'CT009', N'CV009', CAST(N'2022-05-14T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:51:31.560' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0075', N'CT004', N'CV002', CAST(N'2022-05-11T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:28:14.480' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0088', N'CT006', N'CV006', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:14:12.707' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0093', N'CT006', N'CV006', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:14:12.720' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0094', N'CT006', N'CV006', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:14:12.723' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0099', N'CT006', N'CV006', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:14:12.723' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0100', N'CT006', N'CV006', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:14:12.723' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0101', N'CT006', N'CV006', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:14:12.723' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0103', N'CT006', N'CV006', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:14:12.723' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0104', N'CT006', N'CV006', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:14:12.723' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0140', N'CT007', N'CV010', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:45:28.190' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0141', N'CT007', N'CV010', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:45:28.190' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0145', N'CT007', N'CV010', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:45:28.193' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0146', N'CT007', N'CV010', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:45:28.190' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0147', N'CT007', N'CV010', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:45:28.190' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0148', N'CT007', N'CV010', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:45:28.193' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0149', N'CT007', N'CV010', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:45:28.190' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0151', N'CT007', N'CV010', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:45:28.187' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0152', N'CT007', N'CV010', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:45:28.190' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0153', N'CT007', N'CV010', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:45:28.173' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0154', N'CT007', N'CV010', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:45:28.187' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0172', N'CT006', N'CV001', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:13:55.917' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0173', N'CT006', N'CV001', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:13:55.923' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0174', N'CT006', N'CV001', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:13:55.923' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0175', N'CT006', N'CV001', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:13:55.930' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0176', N'CT006', N'CV001', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:13:55.920' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0177', N'CT006', N'CV001', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:13:55.927' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0178', N'CT006', N'CV001', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:13:55.937' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0181', N'CT006', N'CV001', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:13:55.917' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0182', N'CT006', N'CV001', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:13:55.933' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0185', N'CT006', N'CV001', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:13:55.923' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0186', N'CT006', N'CV001', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:13:55.933' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0189', N'CT006', N'CV001', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:13:55.930' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0241', N'CT006', N'CV004', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:14:25.570' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0244', N'CT006', N'CV004', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:14:25.570' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0247', N'CT003', N'CV004', CAST(N'2022-05-13T18:26:19.210' AS DateTime), CAST(N'2022-05-13T18:26:19.217' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0248', N'CT006', N'CV004', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:14:25.570' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0251', N'CT006', N'CV004', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:14:25.570' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0254', N'CT006', N'CV004', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:14:25.557' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0256', N'CT006', N'CV004', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:14:25.570' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0259', N'CT006', N'CV004', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:14:25.570' AS DateTime))
INSERT [dbo].[PHANCONG] ([MaNV], [MaCT], [MaCV], [NgayThamGia], [NgaySuaDoi]) VALUES (N'NV0260', N'CT006', N'CV004', CAST(N'2022-05-13T00:00:00.000' AS DateTime), CAST(N'2022-05-13T18:14:25.570' AS DateTime))
GO
INSERT [dbo].[PHONGBAN] ([MaPB], [TenPB], [NgaySuaDoi], [MaTruongPhong]) VALUES (N'PB01', N'Phòng nhân sự', CAST(N'2022-05-07T03:16:48.157' AS DateTime), N'NV0011')
INSERT [dbo].[PHONGBAN] ([MaPB], [TenPB], [NgaySuaDoi], [MaTruongPhong]) VALUES (N'PB02', N'Phòng kỹ thuật', CAST(N'2022-05-09T14:07:34.373' AS DateTime), N'NV0022')
INSERT [dbo].[PHONGBAN] ([MaPB], [TenPB], [NgaySuaDoi], [MaTruongPhong]) VALUES (N'PB03', N'Phòng thi công', CAST(N'2022-05-09T14:07:54.480' AS DateTime), N'NV0037')
INSERT [dbo].[PHONGBAN] ([MaPB], [TenPB], [NgaySuaDoi], [MaTruongPhong]) VALUES (N'PB04', N'Phòng kinh doanh', CAST(N'2022-05-09T14:08:54.160' AS DateTime), N'NV0048')
INSERT [dbo].[PHONGBAN] ([MaPB], [TenPB], [NgaySuaDoi], [MaTruongPhong]) VALUES (N'PB05', N'Phòng thiết kế', CAST(N'2022-05-09T14:09:06.747' AS DateTime), N'NV0065')
INSERT [dbo].[PHONGBAN] ([MaPB], [TenPB], [NgaySuaDoi], [MaTruongPhong]) VALUES (N'PB06', N'Phòng dự án', CAST(N'2022-05-09T14:09:19.440' AS DateTime), N'NV0005')
INSERT [dbo].[PHONGBAN] ([MaPB], [TenPB], [NgaySuaDoi], [MaTruongPhong]) VALUES (N'PB07', N'Phòng hành chính', CAST(N'2022-05-09T14:09:34.360' AS DateTime), N'NV0057')
GO
INSERT [dbo].[TAIKHOAN] ([TenTK], [MatKhau], [NgaySuaDoi]) VALUES (N'NV0005', N'111111                          ', CAST(N'2022-05-06T16:47:01.840' AS DateTime))
INSERT [dbo].[TAIKHOAN] ([TenTK], [MatKhau], [NgaySuaDoi]) VALUES (N'NV0011', N'111111                          ', CAST(N'2022-05-06T16:47:01.840' AS DateTime))
INSERT [dbo].[TAIKHOAN] ([TenTK], [MatKhau], [NgaySuaDoi]) VALUES (N'NV0022', N'111111                          ', CAST(N'2022-05-06T16:47:01.840' AS DateTime))
INSERT [dbo].[TAIKHOAN] ([TenTK], [MatKhau], [NgaySuaDoi]) VALUES (N'NV0037', N'111111                          ', CAST(N'2022-05-06T16:47:01.840' AS DateTime))
INSERT [dbo].[TAIKHOAN] ([TenTK], [MatKhau], [NgaySuaDoi]) VALUES (N'NV0048', N'111111                          ', CAST(N'2022-05-06T16:47:01.840' AS DateTime))
INSERT [dbo].[TAIKHOAN] ([TenTK], [MatKhau], [NgaySuaDoi]) VALUES (N'NV0057', N'111111                          ', CAST(N'2022-05-06T16:47:01.840' AS DateTime))
INSERT [dbo].[TAIKHOAN] ([TenTK], [MatKhau], [NgaySuaDoi]) VALUES (N'NV0065', N'111111                          ', CAST(N'2022-05-06T16:47:01.840' AS DateTime))
GO
ALTER TABLE [dbo].[CHAMCONG] ADD  CONSTRAINT [DF_CHAMCONG_DaNhan]  DEFAULT ((0)) FOR [DaNhan]
GO
ALTER TABLE [dbo].[CHUYENMON] ADD  CONSTRAINT [DF_CHUYENMON_NgaySuaDoi]  DEFAULT (getdate()) FOR [NgaySuaDoi]
GO
ALTER TABLE [dbo].[CONGTRINH] ADD  CONSTRAINT [DF__CONGTRINH__NgayS__4CA06362]  DEFAULT (getdate()) FOR [NgaySuaDoi]
GO
ALTER TABLE [dbo].[CONGVIEC] ADD  CONSTRAINT [DF__CONGVIEC__NgaySu__4F7CD00D]  DEFAULT (getdate()) FOR [NgaySuaDoi]
GO
ALTER TABLE [dbo].[NHANVIEN] ADD  CONSTRAINT [DF__NHANVIEN__NgaySuaDoi]  DEFAULT (getdate()) FOR [NgaySuaDoi]
GO
ALTER TABLE [dbo].[PHANCONG] ADD  CONSTRAINT [DF__PHANCONG__NgaySu__571DF1D5]  DEFAULT (getdate()) FOR [NgaySuaDoi]
GO
ALTER TABLE [dbo].[PHONGBAN] ADD  CONSTRAINT [DF__PHONGBAN__NgaySuaDoi]  DEFAULT (getdate()) FOR [NgaySuaDoi]
GO
ALTER TABLE [dbo].[TAIKHOAN] ADD  CONSTRAINT [DF__TAIKHOAN__NgaySu__48CFD27E]  DEFAULT (getdate()) FOR [NgaySuaDoi]
GO
ALTER TABLE [dbo].[CHAMCONG]  WITH CHECK ADD  CONSTRAINT [FK_CHAMCONG_NHANVIEN] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NHANVIEN] ([MaNV])
GO
ALTER TABLE [dbo].[CHAMCONG] CHECK CONSTRAINT [FK_CHAMCONG_NHANVIEN]
GO
ALTER TABLE [dbo].[CONGTRINH_CONGVIEC]  WITH CHECK ADD  CONSTRAINT [FK__CONGTRINH___MaCT__5165187F] FOREIGN KEY([MaCT])
REFERENCES [dbo].[CONGTRINH] ([MaCT])
GO
ALTER TABLE [dbo].[CONGTRINH_CONGVIEC] CHECK CONSTRAINT [FK__CONGTRINH___MaCT__5165187F]
GO
ALTER TABLE [dbo].[CONGTRINH_CONGVIEC]  WITH CHECK ADD  CONSTRAINT [FK__CONGTRINH___MaCV__52593CB8] FOREIGN KEY([MaCV])
REFERENCES [dbo].[CONGVIEC] ([MaCV])
GO
ALTER TABLE [dbo].[CONGTRINH_CONGVIEC] CHECK CONSTRAINT [FK__CONGTRINH___MaCV__52593CB8]
GO
ALTER TABLE [dbo].[CONGVIEC]  WITH CHECK ADD  CONSTRAINT [FK_CONGVIEC_CHUYENMON] FOREIGN KEY([MaCM])
REFERENCES [dbo].[CHUYENMON] ([MaCM])
GO
ALTER TABLE [dbo].[CONGVIEC] CHECK CONSTRAINT [FK_CONGVIEC_CHUYENMON]
GO
ALTER TABLE [dbo].[NHANVIEN]  WITH CHECK ADD  CONSTRAINT [FK__NHANVIEN__MaPB__398D8EEE] FOREIGN KEY([MaPB])
REFERENCES [dbo].[PHONGBAN] ([MaPB])
GO
ALTER TABLE [dbo].[NHANVIEN] CHECK CONSTRAINT [FK__NHANVIEN__MaPB__398D8EEE]
GO
ALTER TABLE [dbo].[NHANVIEN]  WITH CHECK ADD  CONSTRAINT [FK_NHANVIEN_CHUYENMON] FOREIGN KEY([MaCM])
REFERENCES [dbo].[CHUYENMON] ([MaCM])
GO
ALTER TABLE [dbo].[NHANVIEN] CHECK CONSTRAINT [FK_NHANVIEN_CHUYENMON]
GO
ALTER TABLE [dbo].[PHANCONG]  WITH CHECK ADD  CONSTRAINT [FK__PHANCONG__MaCT__5535A963] FOREIGN KEY([MaCT])
REFERENCES [dbo].[CONGTRINH] ([MaCT])
GO
ALTER TABLE [dbo].[PHANCONG] CHECK CONSTRAINT [FK__PHANCONG__MaCT__5535A963]
GO
ALTER TABLE [dbo].[PHANCONG]  WITH CHECK ADD  CONSTRAINT [FK__PHANCONG__MaCV__5629CD9C] FOREIGN KEY([MaCV])
REFERENCES [dbo].[CONGVIEC] ([MaCV])
GO
ALTER TABLE [dbo].[PHANCONG] CHECK CONSTRAINT [FK__PHANCONG__MaCV__5629CD9C]
GO
ALTER TABLE [dbo].[PHANCONG]  WITH CHECK ADD  CONSTRAINT [FK__PHANCONG__MaNV__5441852A] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NHANVIEN] ([MaNV])
GO
ALTER TABLE [dbo].[PHANCONG] CHECK CONSTRAINT [FK__PHANCONG__MaNV__5441852A]
GO
ALTER TABLE [dbo].[PHONGBAN]  WITH CHECK ADD  CONSTRAINT [FK_PHONGBAN_NHANVIEN] FOREIGN KEY([MaTruongPhong])
REFERENCES [dbo].[NHANVIEN] ([MaNV])
GO
ALTER TABLE [dbo].[PHONGBAN] CHECK CONSTRAINT [FK_PHONGBAN_NHANVIEN]
GO
ALTER TABLE [dbo].[TAIKHOAN]  WITH CHECK ADD  CONSTRAINT [FK__TAIKHOAN__TenTK__47DBAE45] FOREIGN KEY([TenTK])
REFERENCES [dbo].[NHANVIEN] ([MaNV])
GO
ALTER TABLE [dbo].[TAIKHOAN] CHECK CONSTRAINT [FK__TAIKHOAN__TenTK__47DBAE45]
GO
ALTER TABLE [dbo].[CHAMCONG]  WITH CHECK ADD  CONSTRAINT [CK__CHAMCONG__Nam] CHECK  (([Nam]>=(2012)))
GO
ALTER TABLE [dbo].[CHAMCONG] CHECK CONSTRAINT [CK__CHAMCONG__Nam]
GO
ALTER TABLE [dbo].[CHAMCONG]  WITH CHECK ADD  CONSTRAINT [CK__CHAMCONG__NgayLamViec] CHECK  (([NgayLamViec]>=(0) AND [NgayLamViec]<=(30)))
GO
ALTER TABLE [dbo].[CHAMCONG] CHECK CONSTRAINT [CK__CHAMCONG__NgayLamViec]
GO
ALTER TABLE [dbo].[CHAMCONG]  WITH CHECK ADD  CONSTRAINT [CK__CHAMCONG__Thang] CHECK  (([Thang]>=(1) AND [Thang]<=(12)))
GO
ALTER TABLE [dbo].[CHAMCONG] CHECK CONSTRAINT [CK__CHAMCONG__Thang]
GO
ALTER TABLE [dbo].[CHUYENMON]  WITH CHECK ADD CHECK  (([MaCM] like 'CM[0-9][0-9][0-9][0-9]'))
GO
ALTER TABLE [dbo].[CONGTRINH]  WITH CHECK ADD  CONSTRAINT [CK__CONGTRINH__MaCT] CHECK  (([MaCT] like 'CT[0-9][0-9][0-9]'))
GO
ALTER TABLE [dbo].[CONGTRINH] CHECK CONSTRAINT [CK__CONGTRINH__MaCT]
GO
ALTER TABLE [dbo].[CONGTRINH]  WITH CHECK ADD  CONSTRAINT [CK__CONGTRINH__NgayHoanThanh] CHECK  (([NgayHoanThanhDuKien]>[NgayKhoiCong]))
GO
ALTER TABLE [dbo].[CONGTRINH] CHECK CONSTRAINT [CK__CONGTRINH__NgayHoanThanh]
GO
ALTER TABLE [dbo].[CONGTRINH]  WITH CHECK ADD  CONSTRAINT [CK__CONGTRINH__NgayKhoiCong] CHECK  (([NgayKhoiCong]>[NgayCapPhep]))
GO
ALTER TABLE [dbo].[CONGTRINH] CHECK CONSTRAINT [CK__CONGTRINH__NgayKhoiCong]
GO
ALTER TABLE [dbo].[CONGTRINH]  WITH CHECK ADD  CONSTRAINT [CK__CONGTRINH__TrangThai] CHECK  (([TrangThai]=N'Hoàn thành' OR [TrangThai]=N'Đình chỉ' OR [TrangThai]=N'Tạm hoãn' OR [TrangThai]=N'Chờ khởi công' OR [TrangThai]=N'Thi công'))
GO
ALTER TABLE [dbo].[CONGTRINH] CHECK CONSTRAINT [CK__CONGTRINH__TrangThai]
GO
ALTER TABLE [dbo].[NHANVIEN]  WITH CHECK ADD  CONSTRAINT [CK__NHANVIEN__MaNV] CHECK  (([MaNV] like 'NV[0-9][0-9][0-9][0-9]'))
GO
ALTER TABLE [dbo].[NHANVIEN] CHECK CONSTRAINT [CK__NHANVIEN__MaNV]
GO
ALTER TABLE [dbo].[PHONGBAN]  WITH CHECK ADD  CONSTRAINT [CK__PHONGBAN__MaPB] CHECK  (([MaPB] like '[A-Z][A-Z][0-9][0-9]'))
GO
ALTER TABLE [dbo].[PHONGBAN] CHECK CONSTRAINT [CK__PHONGBAN__MaPB]
GO
ALTER TABLE [dbo].[TAIKHOAN]  WITH CHECK ADD CHECK  (([MatKhau] like '%[^ ][^ ][^ ][^ ][^ ][^ ]'))
GO
/****** Object:  Trigger [dbo].[Trigger_CHAMCONG_NgaySuaDoi]    Script Date: 5/13/2022 7:29:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[Trigger_CHAMCONG_NgaySuaDoi] ON [dbo].[CHAMCONG] FOR UPDATE, INSERT
AS BEGIN
	DECLARE @manv CHAR(6), @thang int, @nam int
	SELECT @manv = MaNV, @thang = Thang, @nam = Nam FROM INSERTED
	UPDATE CHAMCONG
	SET NgaySuaDoi = GETDATE()	/*loi*/
	WHERE @manv = MaNV AND Thang = @thang AND Nam = @nam
END
GO
ALTER TABLE [dbo].[CHAMCONG] ENABLE TRIGGER [Trigger_CHAMCONG_NgaySuaDoi]
GO
/****** Object:  Trigger [dbo].[Trigger_CM_NgaySuaDoi]    Script Date: 5/13/2022 7:29:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[Trigger_CM_NgaySuaDoi] ON [dbo].[CHUYENMON] FOR UPDATE, INSERT
AS BEGIN
	DECLARE @macm CHAR(10)
	SELECT @macm = MaCM FROM INSERTED
	UPDATE CHUYENMON
	SET NgaySuaDoi = GETDATE()
	WHERE @macm = MaCM
END
GO
ALTER TABLE [dbo].[CHUYENMON] ENABLE TRIGGER [Trigger_CM_NgaySuaDoi]
GO
/****** Object:  Trigger [dbo].[Trigger_CT_NgaySuaDoi]    Script Date: 5/13/2022 7:29:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[Trigger_CT_NgaySuaDoi] ON [dbo].[CONGTRINH] FOR UPDATE, INSERT
AS BEGIN
	DECLARE @mact CHAR(5)
	SELECT @mact = MaCT FROM INSERTED
	UPDATE CONGTRINH
	SET NgaySuaDoi = GETDATE()
	WHERE @mact = MaCT
END
GO
ALTER TABLE [dbo].[CONGTRINH] ENABLE TRIGGER [Trigger_CT_NgaySuaDoi]
GO
/****** Object:  Trigger [dbo].[Trigger_CV_NgaySuaDoi]    Script Date: 5/13/2022 7:29:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[Trigger_CV_NgaySuaDoi] ON [dbo].[CONGVIEC] FOR UPDATE, INSERT
AS BEGIN
	DECLARE @macv CHAR(5)
	SELECT @macv = MaCV FROM INSERTED
	UPDATE CONGVIEC
	SET NgaySuaDoi = GETDATE()
	WHERE @macv = MaCV
END
GO
ALTER TABLE [dbo].[CONGVIEC] ENABLE TRIGGER [Trigger_CV_NgaySuaDoi]
GO
/****** Object:  Trigger [dbo].[Trigger_NV_NgaySuaDoi]    Script Date: 5/13/2022 7:29:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[Trigger_NV_NgaySuaDoi] ON [dbo].[NHANVIEN] FOR UPDATE, INSERT
AS BEGIN
	DECLARE @manv CHAR(7)
	SELECT @manv = MaNV FROM INSERTED
	UPDATE NHANVIEN
	SET NgaySuaDoi = GETDATE()
	WHERE @manv = MaNV
END
GO
ALTER TABLE [dbo].[NHANVIEN] ENABLE TRIGGER [Trigger_NV_NgaySuaDoi]
GO
/****** Object:  Trigger [dbo].[Trigger_PC_NgaySuaDoi]    Script Date: 5/13/2022 7:29:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[Trigger_PC_NgaySuaDoi] ON [dbo].[PHANCONG] FOR UPDATE, INSERT
AS BEGIN
	DECLARE @manv CHAR(7), @macv CHAR(5), @mact CHAR(5)
	SELECT @manv = MaNV, @macv = MaCV, @mact = MaCT FROM INSERTED
	UPDATE PHANCONG
	SET NgaySuaDoi = GETDATE()
	WHERE @manv = MaNV AND @macv = MaCV AND @mact = MaCT
END
GO
ALTER TABLE [dbo].[PHANCONG] ENABLE TRIGGER [Trigger_PC_NgaySuaDoi]
GO
/****** Object:  Trigger [dbo].[Trigger_PB_NgaySuaDoi]    Script Date: 5/13/2022 7:29:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--TRIGGER cho NgaySuaDoi
CREATE TRIGGER [dbo].[Trigger_PB_NgaySuaDoi] ON [dbo].[PHONGBAN] FOR UPDATE, INSERT
AS BEGIN
	DECLARE @mapb CHAR(4)
	SELECT @mapb = MaPB FROM INSERTED
	UPDATE PHONGBAN
	SET NgaySuaDoi = GETDATE()
	WHERE @mapb = MaPB
END
GO
ALTER TABLE [dbo].[PHONGBAN] ENABLE TRIGGER [Trigger_PB_NgaySuaDoi]
GO
/****** Object:  Trigger [dbo].[Trigger_XoaPhongBan]    Script Date: 5/13/2022 7:29:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[Trigger_XoaPhongBan] ON [dbo].[PHONGBAN] INSTEAD OF DELETE
AS BEGIN
	DECLARE @MAPB CHAR(4)
	SELECT @MAPB = MAPB FROM DELETED
	IF (@MAPB IS NULL)
		ROLLBACK TRAN
	ELSE BEGIN
		UPDATE NHANVIEN
		SET MAPB = NULL
		WHERE [MaPB] = @MAPB

		DELETE PHONGBAN WHERE MAPB = @MAPB
	END
END
GO
ALTER TABLE [dbo].[PHONGBAN] ENABLE TRIGGER [Trigger_XoaPhongBan]
GO
/****** Object:  Trigger [dbo].[Trigger_TK_NgaySuaDoi]    Script Date: 5/13/2022 7:29:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[Trigger_TK_NgaySuaDoi] ON [dbo].[TAIKHOAN] FOR UPDATE, INSERT
AS BEGIN
	DECLARE @tentk CHAR(7)
	SELECT @tentk = TenTK FROM INSERTED
	UPDATE TAIKHOAN
	SET NgaySuaDoi = GETDATE()
	WHERE @tentk = TenTK
END
GO
ALTER TABLE [dbo].[TAIKHOAN] ENABLE TRIGGER [Trigger_TK_NgaySuaDoi]
GO
USE [master]
GO
ALTER DATABASE [QLLaoDong] SET  READ_WRITE 
GO
