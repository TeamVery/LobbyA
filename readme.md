# Manage

    Copyright (C) 2020-2021 TeamVery

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

> 마인크래프트 Manage 플러그인

> 이 플러그인은 유저관리 목적으로 개발된 플러그인입니다.
>
> 이 플러그인은 [라이선스](https://github.com/TeamVeryMC/Manage/blob/master/LICENSE) 를 지키면 누구나 사용할수 있습니다!
>
> 만약 해당 플러그인을 배포할 예정이라면 [라이선스](https://github.com/TeamVeryMC/Manage/blob/master/LICENSE) 에 따라 배포해주시기 바랍니다.

> 플러그인이 'JDK 16.0'를 기반으로 제작되어 일부 자바 버전에서는 해당 플러그인을 사용할 수 없으니 반드시 자바 버전을 16 이상으로 업데이트해주세요
>
> #### **만약 서버 버전이 '1.13.\*~1.16.\*' 인 경우 [여기](https://github.com/TeamVeryMC/Manage/tree/JAVA11_Legacy) 에서 다운해 주시기 바랍니다.**
> #### **만약 서버 버전이 '1.12.\*' 인 경우 [여기](https://github.com/TeamVeryMC/Manage/tree/MC1.12.2-Legacy) 에서 다운해 주시기 바랍니다.**

---
> * ### LICENSE
> 1. Config-Framework - `MIT License`
>    * https://github.com/TeamVery/Config-Framework
>    * https://github.com/TeamVery/Config-Framework/blob/master/LICENSE
---
> * ### 명령어 목록
> | 명령어 | 설명 |
> |---|---|
> |`manage gui`|Manage의 GUI를 활성화합니다 (**개발 예정**)|
> |`manage set <function> <key>`|`<function>`을 설정합니다|
> |`manage setJoinQuit <function> <key>`|입퇴장 시 발생하는 이벤트를 수정합니다|
> |`manage reload`|Manage플러그인을 리로드합니다|
---
> * ### 지원 함수 목록
> | 함수 | 이벤트 |
> |---|---|
> |IGNORE_ENTITY_SPAWN|아이템을 제외한 모든 엔티티의 스폰을 차단합니다|
> |DISABLE_WEATHER|서버의 날씨를 비활성화합니다|
> |FREEZE_TIME|특정 월드의 시간을 멈추게 합니다 (gamerule 기반)|
> |DISABLE_PLAYER_HUNGER|모든 플레이어의 허기(배고픔)를 비활성화합니다|
> |DISABLE_PVP|모든 플레이어의 PVP를 비활성화합니다|
> |DISABLE_PLAYER_DAMAGE|모든 플레이어의 체력을 비활성화합니다(무적)|
> |DISABLE_PLAYER_MOVEMENT|플레이어의 움직임을 비활성화합니다|
> |DISABLE_INVENTORY_INTERACT|플레이어의 인벤토리 상호작용을 비활성화합니다|
> |DISABLE_CHAT|모든 플레이어의 채팅을 비활성화합니다|
> |DISABLE_PLAYER_INTERACT|플레이어의 상호작용을 비활성화합니다|
> |IGNORE_ITEM_DROP|플레이어가 아이템을 버리는 걸 차단합니다|
> |IGNORE_ITEM_PICKUP|플레이어가 아이템을 획득하는 걸 차단합니다|
> |IGNORE_BLOCK_BREAK|플레이어가 블록을 파괴하는 걸 차단합니다|
> |IGNORE_BLOCK_PLACE|플레이어가 블록을 설치하는 걸 차단합니다|
---
> * ### 펄미션 목록
> | 펄미션 | 범위 | 포함되는 이벤트 |
> |---|---|---|
> |manage.bypass.blockplace|블럭 설치 차단 우회|IGNORE_BLOCK_PLACE|
> |manage.bypass.blockbreak|블럭 파괴 차단 우회|IGNORE_BLOCK_BREAK|
> |manage.bypass.pickupitem|아이템 획득 차단 우회|IGNORE_ITEM_PICKUP|
> |manage.bypass.dropitem|아이템 드롭 차단 우회|IGNORE_ITEM_DROP|
> |manage.bypass.interact|플레이어의 상호작용 비활성화 우회|DISABLE_PLAYER_INTERACT|
> |manage.bypass.movement|플레이어의 움직임 비활성화 우회|DISABLE_PLAYER_MOVEMENT|
> |manage.bypass.chat|플레이어의 채팅 비활성화 우회|DISABLE_CHAT|
> |manage.bypass.inv|플레이어의 인벤토리 상호작용 비활성화 우회|DISABLE_INVENTORY_INTERACT|
> |manage.set|명령어 `manage` 사용|명령어 : `manage`|
---
> ### 테스트 된 버킷
> * Paper
---
> #### 테스트 된 마인크래프트 버전
> * `v1.17.*-R0.1 (1.17.*)`
> * `v1.16.*-R0.1 (1.16.*)` [(Legacy)](https://github.com/TeamVeryMC/Manage/tree/JAVA11_Legacy)
> * `v1.12.*-R0.1 (1.12.*)` [(1.12.2-Legacy)](https://github.com/TeamVeryMC/Manage/tree/MC1.12.2-Legacy)
---