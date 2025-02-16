import React, { useEffect, useState } from "react";
import styled from "styled-components";
import type { NextPage } from "next";
import { useRouter } from "next/router";
import axios from "axios";
import { useRecoilState } from "recoil";

import Nav from "../../components/Nav";
import AdminMenu from "../../components/AdminMenu/AdminMenu";
import { ownerIdState } from "../../recoil/states";

type Menu = {
  id: number;
  name: string;
};

const MyPage: NextPage = () => {
  const [ownerId, setOwnerId] = useRecoilState(ownerIdState);
  const [menuList, setMenuList] = useState<Menu[] | undefined>();
  const [adminContent, setAdminContent] = useState("menu");
  const router = useRouter();
  const { ownerid } = router.query;

  useEffect(() => {
    if (typeof ownerid !== "undefined") {
      setOwnerId(Number(ownerid));
    }
  }, [ownerid]);

  useEffect(() => {
    ownerId !== 0 && getMenuUseOwnerId(ownerId);
  }, [ownerId]);

  const getMenuUseOwnerId = async (ownerid: number) => {
    try {
      const response = await axios.get<Menu[]>(
        "http://localhost:8080/menu/get/owner",
        {
          headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
          },
          params: {
            ownerId: ownerid,
          },
        }
      );
      setMenuList(response.data);
    } catch (err) {
      console.log("error", err);
    }
  };

  return (
    <>
      <Nav />
      <MyPageWrap>
        <SideList>
          <SideItem>
            <li>
              <h2>사장님 정보</h2>
            </li>
            <li>
              <p onClick={() => setAdminContent("profile")}>회원정보</p>
            </li>
            <li>
              <p onClick={() => setAdminContent("menu")}>메뉴판</p>
            </li>
          </SideItem>
        </SideList>
        <AdminContentWrap>
          {(() => {
            switch (adminContent) {
              case "profile":
                return <div>회원정보</div>;
              case "menu":
                return <AdminMenu menuList={menuList} />;
              default:
                return null;
            }
          })()}
        </AdminContentWrap>
      </MyPageWrap>
    </>
  );
};

const MyPageWrap = styled.div`
  background-color: var(--color-light-gray);
  height: 100vh;
  display: flex;
`;

const SideList = styled.div`
  padding: 20px;
  width: 15%;
  background-color: #fff;
  box-shadow: rgba(149, 157, 165, 0.2) 5px 0px 3px 0px;
`;
export default MyPage;

const SideItem = styled.ol`
  h2 {
    font-weight: 700;
    font-size: 1.5rem;
    margin-bottom: 12px;
  }
  p {
    font-size: 1.125rem;
    line-height: 1.125rem;
    margin-bottom: 12px;
    cursor: pointer;
  }
`;

const AdminContentWrap = styled.div`
  padding: 20px;
  flex: 1;
`;
