"use client"

import { useIsMobile } from "@/hooks/use-mobile"

export default function NotFound() {
  const isMobile = useIsMobile()

  return (
    <div className="w-full h-screen flex items-center justify-center">
      <div className="text-center">
        <h1 className={isMobile ? "text-4xl font-bold" : "text-6xl font-bold"}>
          404
        </h1>
        <p
          className={
            isMobile
              ? "mt-4 text-muted-foreground"
              : "mt-4 text-xl text-muted-foreground"
          }
        >
          Упс... Страница не найдена!
        </p>
      </div>
    </div>
  )
}
