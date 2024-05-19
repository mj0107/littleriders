import { useState } from 'react'

import Title from './Title'

declare global {
  interface Window {
    boardState: {
      setInfo: any
    }
    readyState: {
      setInfo: any
    }
    stationState: {
      setInfo: any
    }
  }
}

window.boardState = {
  setInfo: () => {},
}

window.readyState = {
  setInfo: () => {},
}

window.stationState = {
  setInfo: () => {},
}
export default function DrivingPage() {
  const [boardChildList, setBoardChildList] = useState<any[]>([])
  const [readyChildList, setReadyChildList] = useState<any[]>([])

  const [station, setStation] = useState({
    before: '역삼역',
    now: '강남역',
    after: '희준이네집',
  })

  window.boardState.setInfo = setBoardChildList
  window.readyState.setInfo = setReadyChildList
  window.stationState.setInfo = setStation

  return (
    <div className="flex h-full w-full flex-col items-center  bg-lightgreen p-2">
      {/* 제목 부분 */}
      <Title />
      <button
        className="transition-color absolute right-[10%] top-[15%]  bg-yellow p-1 px-8 text-lg active:bg-lightgreen"
        onClick={() => {}}
      >
        운 행 종 료
      </button>
      <div className="h-[30px]"></div>
      {/* 기사, 선탑자 카드 부분 */}
      <div className="flex h-[80%] w-full justify-around">
        <div className="relative flex h-[300px] w-[50%] justify-center rounded-[5px] border-[1px] bg-white font-bold text-black">
          <span className="font text-2xl font-extrabold text-darkgreen">
            이번 정류장
          </span>
          <span className="absolute top-[50%] h-2 w-full -translate-y-1/2 bg-lightgreen"></span>
          <div className="absolute top-[50%] h-[175px] w-[175px] -translate-y-1/2 rounded-full bg-lightgreen"></div>
          <div className="absolute top-[50%] z-10 flex h-[160px] w-[160px] -translate-y-1/2 items-center justify-center rounded-full bg-white p-5 text-center">
            <span className="text-xl">{station.now}</span>
          </div>

          <span className="text-md absolute left-0 top-[40%] text-rose-800">
            이전 정류장
          </span>
          <span className="absolute left-0  top-[52%] w-28 text-left text-lg ">
            {station.before}
          </span>

          <span className="text-md absolute right-0 top-[40%] text-blue-800">
            다음 정류장
          </span>
          <span className="absolute right-0 top-[52%] text-right text-lg">
            {station.after}
          </span>
        </div>
        <div className="flex h-[98%] w-[20%] flex-col items-center overflow-hidden rounded-t-xl bg-white">
          <div className=" flex w-full justify-center bg-yellow p-2 font-bold">
            원생 탑승 현황
          </div>
          <ul className="flex h-[100%] w-full flex-col overflow-y-auto">
            {boardChildList.map((item) => {
              return (
                <li className=" flex w-full justify-center border-b-2 border-lightyellow">
                  {item.name}
                </li>
              )
            })}
          </ul>
        </div>
        <div className="flex h-[98%] w-[20%] flex-col items-center overflow-hidden rounded-t-xl bg-white">
          <div className=" flex w-full justify-center bg-yellow p-2 font-bold">
            승/하차 대기 현황
          </div>
          <ul className="flex h-[100%] w-full flex-col overflow-y-auto">
            {readyChildList.map((item) => {
              return (
                <li className="flex w-full justify-center border-b-2 border-lightyellow">
                  {item.name}
                </li>
              )
            })}
          </ul>
        </div>
      </div>
    </div>
  )
}
