import { memo } from 'react'

import { formatStringToPhoneNumber } from '@utils/formatUtils'

import { SSE_DriverInfo } from '@types'
import { IoCallSharp } from 'react-icons/io5'

interface Props {
  data: SSE_DriverInfo
}

export default memo(function DriverSmallCard({ data }: Props) {
  return (
    <div className="border-ligtgray relative flex h-[100%] w-[43%]  flex-col items-center justify-center rounded-xl  border-[1px] border-lightgray p-[2%] ">
      {/* 직원 사진 */}
      <img
        src={`/api/content/${data.image}`}
        // src={mock}
        alt=""
        className="h-[25%] w-[25%] rounded-full border-[1px] border-lightgreen"
      />
      {/* 직원 정보 */}
      <div className=" ml-[3%] flex flex-col items-center">
        <strong className="text-[80%]">{data.name} 기사님</strong>
        <span className="text-[70%] text-darkgray">
          {formatStringToPhoneNumber(data.phoneNumber)}
        </span>
      </div>
      <a
        href={`tel:${data.phoneNumber}`}
        className="flex w-full items-center justify-center rounded-md bg-lightgreen py-[1px] text-[70%] font-bold text-white"
      >
        <IoCallSharp /> 전화 걸기
      </a>
    </div>
  )
})
